package centennial.restaurant

import android.content.Context
import javax.xml.parsers.DocumentBuilderFactory

data class Restaurant(val title: String, val cuisine: String, val location: String, val phone: String, val website: String?)

fun parse_xml(context: Context): MutableList<Restaurant>
{
	val restaurants = mutableListOf<Restaurant>()
	var title: String?
	var cuisine: String?
	var location: String?
	var phone: String?
	var website: String?
	val datasource = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(context.assets.open("restaurant.xml"))
	val root = datasource.documentElement.childNodes
	for(i in 0 until root.length)
	{
		val item = root.item(i).childNodes
		title = null
		cuisine = null
		location = null
		phone = null
		website = null
		for(n in 0 until item.length)
		{
			item.item(n).run{
				when(this.nodeName)
				{
					"title"->title = this.textContent
					"cuisine"->cuisine = this.textContent
					"location"->location = this.textContent
					"phone"->phone = this.textContent
					"website"->website = this.textContent
				}
			}
		}
		if(title != null && cuisine != null && location != null && phone != null)
			restaurants.add(Restaurant(title!!, cuisine!!, location!!, phone!!, website))
	}
	return restaurants
}

lateinit var restaurants: MutableList<Restaurant>