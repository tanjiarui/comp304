package example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AIActivity : AppCompatActivity()
{
	private var message = StringBuilder("AIActivity")
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.ai_activity)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		toolbar.setNavigationOnClickListener{finish()}
	}
	override fun onStart()
	{
		super.onStart()
		message.append("\nonStart() Executed")
		val bottom_text: TextView = findViewById(R.id.bottom_text)
		bottom_text.text = message
	}
	override fun onResume()
	{
		super.onResume()
		message.append("\nonResume() Executed")
		val bottom_text: TextView = findViewById(R.id.bottom_text)
		bottom_text.text = message
	}
	override fun onPause()
	{
		super.onPause()
		message.append("\nonPause() Executed")
		val bottom_text: TextView = findViewById(R.id.bottom_text)
		bottom_text.text = message
	}
	override fun onStop()
	{
		super.onStop()
		message.append("\nonStop() Executed")
		val bottom_text: TextView = findViewById(R.id.bottom_text)
		bottom_text.text = message
	}
	override fun onDestroy()
	{
		super.onDestroy()
		message.append("\nonDestroy() Executed")
		val bottom_text: TextView = findViewById(R.id.bottom_text)
		bottom_text.text = message
	}
}