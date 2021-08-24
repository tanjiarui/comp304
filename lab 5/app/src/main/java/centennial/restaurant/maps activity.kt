package centennial.restaurant

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import centennial.restaurant.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest

class CustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter, View(context)
{
	private fun render(marker: Marker, view: View)
	{
		val title = view.findViewById<TextView>(R.id.marker_title)
		title.text = marker.title
		val snippet = view.findViewById<TextView>(R.id.snippet)
		snippet.text = marker.snippet
	}

	override fun getInfoWindow(marker: Marker?): View?
	{
		val view = inflate(context, R.layout.info_window, findViewById(R.id.info_window))
		marker?.let{render(it, view)}
		return view
	}

	override fun getInfoContents(marker: Marker?): View?
	{
		return null
	}
}

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPoiClickListener
{
	private lateinit var map: GoogleMap
	private lateinit var binding: ActivityMapsBinding

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)

		binding = ActivityMapsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		val fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
		fragment.getMapAsync(this)
	}

	// parse the restaurant object and add the marker
	private fun add_marker(map: GoogleMap, restaurant: Restaurant)
	{
		val location = LatLng(restaurant.location.split(",")[0].toDouble(),restaurant.location.split(",")[1].toDouble())
		val title = restaurant.title
		val phone = restaurant.phone
		val snippet: String = if(restaurant.website != null) "phone: $phone\nwebsite: ${restaurant.website}" else "phone: $phone"
		map.addMarker(MarkerOptions().position(location).title(title).snippet(snippet)).showInfoWindow()
	}

	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	override fun onMapReady(googleMap: GoogleMap)
	{
		map = googleMap
		// redesign the info window
		map.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
		map.setOnPoiClickListener(this)
		map.isBuildingsEnabled = true
		map.isIndoorEnabled = true
		map.isTrafficEnabled = true
		map.setOnMapClickListener{map.clear()}
		binding.mapType.onItemSelectedListener = object: AdapterView.OnItemSelectedListener
		{
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
			{
				when((view as TextView).text)
				{
					"normal"->map.mapType = GoogleMap.MAP_TYPE_NORMAL
					"satellite"->map.mapType = GoogleMap.MAP_TYPE_SATELLITE
					"terrain"->map.mapType = GoogleMap.MAP_TYPE_TERRAIN
					"hybrid"->map.mapType = GoogleMap.MAP_TYPE_HYBRID
				}
			}
			override fun onNothingSelected(parent: AdapterView<*>?){}
		}
		// extract the restaurant info and mark it
		val title = intent.getStringExtra("restaurant")
		restaurants.find { it.title == title }.let {
			if (it != null)
				add_marker(map, it)
		}
	}

	override fun onPoiClick(poi: PointOfInterest?)
	{
		map.clear()
		poi?.let{map.addMarker(MarkerOptions().position(it.latLng).title("point of interest").snippet("name: ${it.name}\nplace id: ${it.placeId}")).showInfoWindow()}
	}
}