package centennial.messenger

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import centennial.messenger.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity()
{
	private lateinit var binding: ActivityServiceBinding
	private val receiver = object: BroadcastReceiver()
	{
		override fun onReceive(context: Context, intent: Intent)
		{
			val action = intent.action
			if(action.equals(DemoService.INFO_INTENT))
				binding.service.text = intent.getStringExtra(DemoService.INFO_INTENT)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityServiceBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	override fun onResume()
	{
		super.onResume()
		registerReceiver(receiver, IntentFilter(DemoService.INFO_INTENT))
	}

	fun service(button: View)
	{
		if((button as Button).text.equals("start service"))
			startService(Intent(baseContext, DemoService::class.java))
		else
		{
			stopService(Intent(baseContext, DemoService::class.java))
			binding.service.text = null
		}
	}
}