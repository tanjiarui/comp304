package example.myapplication

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class FramedAnimation : AppCompatActivity()
{
	lateinit var image_view: ImageView
	lateinit var button: Button
	var animation_drawable = AnimationDrawable()
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.framed_animation)
		image_view = findViewById(R.id.frame)
		button = findViewById(R.id.run)
	}
	override fun onWindowFocusChanged(hasFocus: Boolean)
	{
		super.onWindowFocusChanged(hasFocus)
		animation_drawable = image_view.drawable as AnimationDrawable
	}
	// bind with the button
	fun run(view: View)
	{
		when((view as Button).text)
		{
			"start" ->
			{
				animation_drawable.start()
				button.text = resources.getString(R.string.stop)
			}
			"stop" ->
			{
				animation_drawable.stop()
				button.text = resources.getString(R.string.start)
			}
		}
	}
}