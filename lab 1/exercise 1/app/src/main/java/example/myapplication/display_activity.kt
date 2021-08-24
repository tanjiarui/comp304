package example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class DisplayActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.display_message)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		toolbar.setNavigationOnClickListener{finish()}
	}

	override fun onStart()
	{
		super.onStart()
		// Get the Intent that started this activity and extract the string
		val message = intent.getStringExtra(EXTRA_MESSAGE)

		// Capture the layout's TextView and set the string as its text
		findViewById<TextView>(R.id.textView).apply{text = message}
	}
}