package example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class TweenedAnimation : AppCompatActivity()
{
	lateinit var earth: ImageView
	lateinit var solar: ImageView
	lateinit var button: Button
	lateinit var earth_animation: Animation
	lateinit var solar_animation: Animation
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.tweened_animation)
		earth = findViewById(R.id.earth)
		solar = findViewById(R.id.solar)
		button = findViewById(R.id.run)
		earth_animation = AnimationUtils.loadAnimation(this, R.anim.earth_animation)
		solar_animation = AnimationUtils.loadAnimation(this, R.anim.solar_animation)
		// hide earth and set the text of button as "start" when the earth animation ends
		earth_animation.setAnimationListener(object: Animation.AnimationListener
		{
			override fun onAnimationStart(animation: Animation?){}
			override fun onAnimationEnd(animation: Animation?)
			{
				earth.visibility = View.INVISIBLE
				button.text = resources.getString(R.string.start)
			}
			override fun onAnimationRepeat(animation: Animation?){}
		})
	}
	// bind with the button
	fun run(view: View)
	{
		when((view as Button).text)
		{
			"start" ->
			{
				earth.visibility = View.VISIBLE
				earth.startAnimation(earth_animation)
				solar.startAnimation(solar_animation)
				button.text = resources.getString(R.string.stop)
			}
			"stop" ->
			{
				earth.visibility = View.INVISIBLE
				earth.clearAnimation()
				solar.clearAnimation()
				button.text = resources.getString(R.string.start)
			}
		}
	}
}