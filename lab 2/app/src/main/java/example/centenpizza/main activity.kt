package example.centenpizza

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		my_app.add_activity(this)
	}
	// order event handler
	fun order_click(button: View)
	{
		if((button as MaterialButton).text.equals("order"))
		{
			val intent = Intent(this, ChooseTypeActivity::class.java)
			startActivity(intent)
		}
	}
}