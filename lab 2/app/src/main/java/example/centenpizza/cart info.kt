package example.centenpizza

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class RecyclerViewAdapter(val context: Context?, val list: MutableList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
	{
		var type: TextView? = null
		var size: Spinner? = null
		var number: EditText? = null
		var style: RadioGroup? = null
		init
		{
			type = itemView.findViewById(R.id.pizza_type)
			size = itemView.findViewById(R.id.size)
			number = itemView.findViewById(R.id.number)
			style = itemView.findViewById(R.id.style)
		}
	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder
	{
		val view:View = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
		return ViewHolder(view)
	}
	override fun getItemCount() : Int { return list.size }
	override fun onBindViewHolder(holder: ViewHolder, position: Int)
	{
		holder.type?.text = list[position]
	}
}

class CartDetail : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.cart_detail)
		my_app.add_activity(this)
		val canadian = getSharedPreferences("cart",0).contains("Canadian Pizza")
		val chicken = getSharedPreferences("cart",0).contains("Chicken Caesar")
		val hawaiian = getSharedPreferences("cart",0).contains("Hawaiian Pizza")
		val smokey = getSharedPreferences("cart",0).contains("Smokey Maple Bacon")
		val veggie = getSharedPreferences("cart",0).contains("Veggie Lover")
		val types = mutableListOf<String>()
		if(canadian) types.add("Canadian Pizza")
		if(chicken) types.add("Chicken Caesar")
		if(hawaiian) types.add("Hawaiian Pizza")
		if(smokey) types.add("Smokey Maple Bacon")
		if(veggie) types.add("Veggie Lover")
		val recycler_view = findViewById<RecyclerView>(R.id.item_list)
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.adapter = RecyclerViewAdapter(this, types)
	}
	// move to checkout activity
	fun checkout(button: View)
	{
		if((button as MaterialButton).text.equals("checkout"))
		{
			val recycler_view = findViewById<RecyclerView>(R.id.item_list)
			for(i in 0 until recycler_view.childCount)
			{
				val layout = recycler_view.getChildAt(i)
				val type = layout.findViewById<TextView>(R.id.pizza_type).text
				val size = layout.findViewById<Spinner>(R.id.size).selectedItem
				val id = layout.findViewById<RadioGroup>(R.id.style).checkedRadioButtonId
				// the loop will restart if no style or number inputted
				val style = if(id != -1) layout.findViewById<RadioButton>(id).text else { Toast.makeText(this, "please choose the style",Toast.LENGTH_LONG).show(); return }
				val number = layout.findViewById<EditText>(R.id.number).text.ifEmpty { Toast.makeText(this, "please choose the number",Toast.LENGTH_LONG).show(); return }
				getSharedPreferences("cart",0).edit().putString(type as String?, listOf(size as String, style as String, number.toString()).toString()).apply()
			}
			val intent = Intent(this, Checkout::class.java)
			startActivity(intent)
		}
	}
}