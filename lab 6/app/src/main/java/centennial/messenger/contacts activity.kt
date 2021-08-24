package centennial.messenger

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
import centennial.messenger.databinding.ActivityContactsBinding

class ListViewAdapter(private val context: Context, private val list: Array<String>) : BaseAdapter()
{
	private val colors = mutableListOf(Color.GREEN, Color.BLUE)
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

class Contacts : AppCompatActivity()
{
	private lateinit var binding: ActivityContactsBinding
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityContactsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val contacts = resources.getStringArray(R.array.contacts)
		binding.contacts.adapter = ListViewAdapter(this, contacts)
		binding.contacts.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
			val name = contacts[position]
			val intent = Intent(this, MessageActivity::class.java)
			intent.putExtra("name", name)
			startActivity(intent)
		}
	}
}