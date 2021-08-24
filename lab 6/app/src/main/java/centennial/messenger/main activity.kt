package centennial.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import centennial.messenger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
	private lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
	fun run(button: View)
	{
		intent = if((button as Button).text.equals("exercise 1"))
			Intent(this, Contacts::class.java)
		else
			Intent(this, ServiceActivity::class.java)
		startActivity(intent)
	}
}