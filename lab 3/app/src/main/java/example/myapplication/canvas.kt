package example.myapplication

import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi

class CanvasActivity : AppCompatActivity()
{
	val paint = Paint()
	lateinit var bitmap: Bitmap
	lateinit var canvas: Canvas
	lateinit var drawing_view: ImageView
	var cur_x: Float = 0f
	var cur_y: Float = 0f
	lateinit var x_postion: TextView
	lateinit var y_postion: TextView
	val x_increment: Float = 100f
	val y_increment: Float = 100f
	fun clear_canvas()
	{
		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
		cur_x = 0f
		cur_y = 0f
		canvas.drawPoint(0f, 0f, paint)
		x_postion.text = String.format(this.resources.getString(R.string.x_text), cur_x)
		y_postion.text = String.format(this.resources.getString(R.string.y_text), cur_y)
		drawing_view.invalidate()
	}
	// bind with the clear button
	fun click_to_clear(button: View)
	{
		if((button as Button).text.equals("clear"))
			clear_canvas()
	}
	@RequiresApi(Build.VERSION_CODES.R)
	fun canvas_init()
	{
		// config paint
		paint.color = Color.RED
		paint.strokeWidth = 10f
		val metrics = DisplayMetrics()
		this.display?.getRealMetrics(metrics)
		bitmap = Bitmap.createBitmap(metrics.widthPixels, metrics.heightPixels, Bitmap.Config.ARGB_8888)
		canvas = Canvas(bitmap)
		drawing_view = findViewById(R.id.canvas)
		drawing_view.setImageBitmap(bitmap)
		x_postion = findViewById(R.id.x_position)
		y_postion = findViewById(R.id.y_position)
		clear_canvas()
		// radio group listener
		findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { _,id ->
			when(id)
			{
				R.id.red_button -> paint.color = Color.RED
				R.id.green_button -> paint.color = Color.GREEN
				R.id.yellow_button -> paint.color = Color.YELLOW
			}}
		// spinner listener
		findViewById<Spinner>(R.id.thickness_spinner).onItemSelectedListener = object: AdapterView.OnItemSelectedListener
		{
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
			{
				paint.strokeWidth = resources.getStringArray(R.array.thickness_array)[position].toFloat()
			}
			override fun onNothingSelected(parent: AdapterView<*>?){}
		}
	}
	fun draw_line(x_increment: Float, y_increment: Float)
	{
		x_postion.text = String.format(this.resources.getString(R.string.x_text), cur_x)
		y_postion.text = String.format(this.resources.getString(R.string.y_text), cur_y)
		canvas.drawLine(cur_x, cur_y, cur_x + x_increment, cur_y + y_increment, paint)
		cur_x += x_increment
		cur_y += y_increment
	}
	// bind with the up view
	fun draw_line_up(button: View)
	{
		if((button as ImageView).contentDescription.equals("arrow"))
		{
			if(cur_y < y_increment)
			Toast.makeText(this, "reached edge", Toast.LENGTH_SHORT).show()
			else
			{
				draw_line(0f, -y_increment)
				drawing_view.invalidate()
			}
		}
	}
	// bind with the down view
	fun draw_line_down(button: View)
	{
		if((button as ImageView).contentDescription.equals("arrow"))
		{
			if (cur_y + y_increment > bitmap.height)
				Toast.makeText(this, "reached edge", Toast.LENGTH_SHORT).show()
			else
			{
				draw_line(0f, y_increment)
				drawing_view.invalidate()
			}
		}
	}
	// bind with the left view
	fun draw_line_left(button: View)
	{
		if((button as ImageView).contentDescription.equals("arrow"))
		{
			if (cur_x < x_increment)
				Toast.makeText(this, "reached edge", Toast.LENGTH_SHORT).show()
			else
			{
				draw_line(-x_increment, 0f)
				drawing_view.invalidate()
			}
		}
	}
	// bind with the right view
	fun draw_line_right(button: View)
	{
		if((button as ImageView).contentDescription.equals("arrow"))
		{
			if(cur_x + x_increment > bitmap.width)
				Toast.makeText(this, "reached edge", Toast.LENGTH_SHORT).show()
			else
			{
				draw_line(x_increment, 0f)
				drawing_view.invalidate()
			}
		}
	}
	@RequiresApi(Build.VERSION_CODES.R)
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.canvas)
		canvas_init()
	}
}