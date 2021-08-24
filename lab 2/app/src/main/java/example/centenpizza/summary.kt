package example.centenpizza

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import com.google.android.material.button.MaterialButton
import kotlin.system.exitProcess

class Summary : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.summary)
		my_app.add_activity(this)
		val name = getSharedPreferences("cart",0).getString("name",null)
		val phone = getSharedPreferences("cart",0).getString("phone",null)
		val street = getSharedPreferences("cart",0).getString("street",null)
		val city = getSharedPreferences("cart",0).getString("city",null)
		val zip = getSharedPreferences("cart",0).getString("zip",null)
		val note = getSharedPreferences("cart",0).getString("note",null)
		findViewById<CheckedTextView>(R.id.name_summary).text = name
		findViewById<CheckedTextView>(R.id.phone_summary).text = phone
		findViewById<CheckedTextView>(R.id.street_summary).text = street
		findViewById<CheckedTextView>(R.id.city_summary).text = city
		findViewById<CheckedTextView>(R.id.zip_summary).text = zip
		if(note != null)
		{
			val note_text = findViewById<CheckedTextView>(R.id.note_summary)
			note_text.visibility = View.VISIBLE
			note_text.text = note
		}
	}
	override fun onDestroy()
	{
		super.onDestroy()
		getSharedPreferences("cart",0).edit().clear().apply()
	}
	// clear the preference file and exit the app
	@SuppressLint("ApplySharedPref")
	fun clear_cart(button: View)
	{
		if((button as MaterialButton).text.equals("exit"))
		{
			getSharedPreferences("cart",0).edit().clear().commit()
			my_app.onTerminate()
			exitProcess(0)
		}
	}
}