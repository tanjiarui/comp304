package centennial.restaurant

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import centennial.restaurant.databinding.ActivityCuisineBinding

class ListViewAdapter(private val context: Context, private val list: Array<String>) : BaseAdapter()
{
	private val colors = mutableListOf(Color.GRAY, Color.LTGRAY)
	override fun getCount(): Int { return list.size }
	override fun getItem(position: Int): String { return list[position] }
	override fun getItemId(position: Int): Long { return position.toLong() }
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
	{
		val view: View?
		val text: TextView?
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
			text = view?.findViewById(R.id.item)
			view.tag = text
		}
		else
		{
			view = convertView
			text = view.tag as TextView?
		}
		if (text != null)
			text.text = list[position]
		view?.setBackgroundColor(colors[position%2])
		return view
	}
}

class ShowCuisine : AppCompatActivity()
{
	private lateinit var binding: ActivityCuisineBinding
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityCuisineBinding.inflate(layoutInflater)
		setContentView(binding.root)
		// load restaurants' info
		restaurants = parse_xml(this)
		// setup the cuisine listview
		val cuisine_list: Array<String> = resources.getStringArray(R.array.cuisine)
		binding.cuisine.adapter = ListViewAdapter(this, cuisine_list)
		binding.cuisine.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
			val cuisine = cuisine_list[position]
			val intent = Intent(this, ShowRestaurant::class.java)
			intent.putExtra("cuisine", cuisine)
			startActivity(intent)
		}
	}
}