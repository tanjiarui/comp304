package example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
	// exercises entrance
	fun get_start(button: View)
	{
		when((button as Button).text)
		{
			"exercise 1" ->
			{
				val intent = Intent(this, CanvasActivity::class.java)
				startActivity(intent)
			}
			"exercise 2" ->
			{
				val intent = Intent(this, FramedAnimation::class.java)
				startActivity(intent)
			}
			"exercise 3" ->
			{
				val intent = Intent(this, TweenedAnimation::class.java)
				startActivity(intent)
			}
		}
	}
}