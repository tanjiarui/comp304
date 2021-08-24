package example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
	fun sendMessage(button: View)
	{
		if ((button as MaterialButton).text.equals("send"))
		{
			val text = findViewById<TextInputEditText>(R.id.edit_text)
			val message = text.text.toString()
			val intent = Intent(this, DisplayActivity::class.java).apply { putExtra(EXTRA_MESSAGE, message) }
			startActivity(intent)
		}
	}
}