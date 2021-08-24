package example.calorie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val intent = Intent(this, CanvasActivity::class.java)
		val spinner = findViewById<Spinner>(R.id.spinner)
		spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener
		{
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
			{
				// move to the next activity only when choosing the valid exercise
				if(spinner.selectedItem.toString() != "type")
				{
					intent.putExtra("type", spinner.selectedItem.toString())
					startActivity(intent)
				}
			}
			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
	}
}