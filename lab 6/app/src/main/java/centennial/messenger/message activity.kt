package centennial.messenger

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import centennial.messenger.databinding.ActivityMessageBinding

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit)
{
	this.addTextChangedListener(object : TextWatcher
	{
		override fun afterTextChanged(editable: Editable?)
		{
			afterTextChanged.invoke(editable.toString())
		}
		override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
		override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
	})
}

class MessageActivity : AppCompatActivity()
{
	private lateinit var binding: ActivityMessageBinding
	private val sent_receiver = object: BroadcastReceiver()
	{
		override fun onReceive(context: Context, intent: Intent)
		{
			when(resultCode)
			{
				Activity.RESULT_OK->Toast.makeText(baseContext, "sms sent", Toast.LENGTH_LONG).show()
				SmsManager.RESULT_ERROR_GENERIC_FAILURE->Toast.makeText(baseContext, "generic failure", Toast.LENGTH_SHORT).show()
				SmsManager.RESULT_ERROR_NO_SERVICE->Toast.makeText(baseContext, "no service", Toast.LENGTH_SHORT).show()
				SmsManager.RESULT_ERROR_NULL_PDU->Toast.makeText(baseContext, "null PDU", Toast.LENGTH_SHORT).show()
				SmsManager.RESULT_ERROR_RADIO_OFF->Toast.makeText(baseContext, "radio off", Toast.LENGTH_SHORT).show()
			}
		}
	}
	private val delivered_receiver = object: BroadcastReceiver()
	{
		override fun onReceive(context: Context, intent: Intent)
		{
			when(resultCode)
			{
				Activity.RESULT_OK->Toast.makeText(baseContext, "sms delivered", Toast.LENGTH_LONG).show()
				Activity.RESULT_CANCELED->Toast.makeText(baseContext, "sms not delivered", Toast.LENGTH_LONG).show()
			}
		}
	}
	private lateinit var sent_flag: PendingIntent
	private lateinit var delivered_flag: PendingIntent
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityMessageBinding.inflate(layoutInflater)
		setContentView(binding.root)
		ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS, android.Manifest.permission.SEND_SMS, android.Manifest.permission.READ_PHONE_STATE), 1)
		binding.name.text = intent.getStringExtra("name")
		sent_flag = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), 0)
		delivered_flag = PendingIntent.getBroadcast(this, 0, Intent("SMS_DELIVERED"), 0)
		// the phone number must not be empty
		binding.phone.afterTextChanged {
			binding.send.isEnabled = it.isNotEmpty()
		}
	}
	override fun onResume()
	{
		super.onResume()
		registerReceiver(sent_receiver, IntentFilter("SMS_SENT"))
		registerReceiver(delivered_receiver, IntentFilter("SMS_DELIVERED"))
	}
	override fun onPause()
	{
		super.onPause()
		unregisterReceiver(sent_receiver)
		unregisterReceiver(delivered_receiver)
	}
	fun send_message(button: View)
	{
		if((button as Button).text.equals("send"))
		{
			val phone = binding.phone.text.toString()
			val message = binding.message.text.toString()
			val sms = SmsManager.getDefault()
			sms.sendTextMessage(phone, null, message, sent_flag, delivered_flag)
		}
	}
}