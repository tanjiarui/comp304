package centennial.restaurant

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import centennial.restaurant.databinding.ActivityRestaurantBinding

class ShowRestaurant : AppCompatActivity()
{
	private lateinit var binding: ActivityRestaurantBinding
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityRestaurantBinding.inflate(layoutInflater)
		setContentView(binding.root)
		var title: Array<String> = emptyArray()
		val cuisine = intent.getStringExtra("cuisine")
		// query restaurants based on the clicked cuisine
		restaurants.filter { it.cuisine == cuisine }.forEach { title = title.plus(it.title) }
		// setup the restaurant listview
		binding.restaurant.adapter = ListViewAdapter(this, title)
		binding.restaurant.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
			val item = title[position]
			val intent = Intent(this, MapsActivity::class.java)
			intent.putExtra("restaurant", item)
			startActivity(intent)
		}
	}
}