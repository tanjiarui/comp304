package example.calorie

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CanvasActivity : AppCompatActivity()
{
	lateinit var type: String
	val rect_paint = Paint()
	val text_paint = Paint()
	lateinit var bitmap: Bitmap
	lateinit var canvas: Canvas
	lateinit var drawing_view: ImageView
	fun draw_canvas()
	{
		// config paint
		when(type)
		{
			"jumping rope" -> rect_paint.color = Color.BLUE
			"swimming" -> rect_paint.color = Color.RED
			"bike riding" -> rect_paint.color = Color.YELLOW
			"walking" -> rect_paint.color = Color.GREEN
			"running" -> rect_paint.color = Color.MAGENTA
		}
		rect_paint.strokeWidth = 10f
		text_paint.color = Color.WHITE
		text_paint.textAlign = Paint.Align.CENTER
		text_paint.textSize = 60f
		text_paint.strokeWidth = 60f
		bitmap = Bitmap.createBitmap(900, 500, Bitmap.Config.ARGB_8888)
		bitmap.eraseColor(Color.BLACK)
		canvas = Canvas(bitmap)
		drawing_view = findViewById(R.id.canvas)
		drawing_view.setImageBitmap(bitmap)
		canvas.drawRect(0f, 200f, 900f, 300f, rect_paint)
		canvas.drawText(type, 450f, 270f, text_paint)
	}
	fun draw_legend()
	{
		val text_paint = Paint()
		lateinit var calorie: String
		// config paint
		when(type)
		{
			"jumping rope" -> calorie = "750 kcal"
			"swimming", "running" -> calorie = "600 kcal"
			"bike riding" -> calorie = "500 kcal"
			"walking" -> calorie = "300 kcal"
		}
		text_paint.color = Color.BLACK
		text_paint.textAlign = Paint.Align.CENTER
		text_paint.textSize = 40f
		text_paint.strokeWidth = 40f
		val bitmap = Bitmap.createBitmap(900, 100, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(bitmap)
		val drawing_view = findViewById<ImageView>(R.id.legend)
		drawing_view.setImageBitmap(bitmap)
		canvas.drawRect(200f, 0f, 600f, 100f, rect_paint)
		canvas.drawText(calorie, 700f, 60f, text_paint)
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.canvas)
		type = intent.getStringExtra("type").toString()
		draw_canvas()
		draw_legend()
	}
}