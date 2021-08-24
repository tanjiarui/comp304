package example.centenpizza

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ListViewAdapter(val context: Context?, val list: MutableList<MutableMap<String, Any>>) : BaseAdapter()
{
	override fun getCount(): Int { return list.size }
	override fun getItem(position: Int): Any { return list[position] }
	override fun getItemId(position: Int): Long { return position.toLong() }
	// set pizza details to textview in cart
	@SuppressLint("SetTextI18n")
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
	{
		val view: View?
		val text: TextView?
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.text_view, parent, false)
			text = view?.findViewById(R.id.pizza)
			view.tag = text
		}
		else
		{
			view = convertView
			text = view.tag as TextView?
		}
		if (text != null)
			text.text = "item: " + list[position].keys.toString().trim('[',']') + '\n' + list[position].values.toString().trim('[',']')
		return view
	}
}

class Checkout : AppCompatActivity()
{
	// dealing the contradiction between scrollview and listview
//	fun setListviewHeight(listview : ListView)
//	{
//		val adapter = listview.adapter ?: return
//		var total_height = 0
//		for(i in 1 until adapter.count)
//		{
//			val item = adapter.getView(i,null,listview)
//			item.measure(0,0) // get item height
//			total_height += item.measuredHeight
//		}
//		listview.layoutParams.height = total_height + (listview.dividerHeight * (adapter.count-1)) + listview.paddingTop + listview.paddingBottom
//	}
	// edittext of card-related would vary with radio buttons
	fun dynamic_rendering(group: RadioGroup)
	{
		group.setOnCheckedChangeListener { _,id ->
			val button: RadioButton = findViewById(id)
			when(button.text)
			{
				"cash" , null->
				{
					findViewById<EditText>(R.id.card_number).visibility = View.INVISIBLE
					findViewById<EditText>(R.id.month).visibility = View.INVISIBLE
					findViewById<EditText>(R.id.year).visibility = View.INVISIBLE
					findViewById<EditText>(R.id.cvc).visibility = View.INVISIBLE
				}
				"credit card" ->
				{
					findViewById<EditText>(R.id.card_number).visibility = View.VISIBLE
					findViewById<EditText>(R.id.month).visibility = View.VISIBLE
					findViewById<EditText>(R.id.year).visibility = View.VISIBLE
					findViewById<EditText>(R.id.cvc).visibility = View.VISIBLE
				}
				"debit card" ->
				{
					findViewById<EditText>(R.id.card_number).visibility = View.VISIBLE
					findViewById<EditText>(R.id.month).visibility = View.VISIBLE
					findViewById<EditText>(R.id.year).visibility = View.VISIBLE
					findViewById<EditText>(R.id.cvc).visibility = View.INVISIBLE
				}
			}
		}
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.checkout)
		my_app.add_activity(this)
		val pizzas = mutableListOf<MutableMap<String, Any>>()
		val cart_item = listOf("Canadian Pizza", "Chicken Caesar", "Hawaiian Pizza", "Smokey Maple Bacon", "Veggie Lover")
		getSharedPreferences("cart",0).all.forEach{ (key, value) -> if(cart_item.contains(key)) pizzas.add(mutableMapOf(key to value.toString())) }
		val list = findViewById<ListView>(R.id.pizza_list)
		list.adapter = ListViewAdapter(this, pizzas)
		val group = findViewById<RadioGroup>(R.id.payment_group)
		dynamic_rendering(group)
		// only 1-12 is a valid input for month
		findViewById<EditText>(R.id.month).addTextChangedListener(object: TextWatcher {
			override fun beforeTextChanged(source: CharSequence?, start: Int, count: Int, after: Int) {}

			override fun onTextChanged(source: CharSequence?, start: Int, before: Int, count: Int) {}

			override fun afterTextChanged(source: Editable)
			{
				val month = source.toString().toIntOrNull()
				if(month != null && (month < 1 || month > 12))
				{
					findViewById<EditText>(R.id.month).removeTextChangedListener(this)
					Toast.makeText(this@Checkout, "invalid month",Toast.LENGTH_LONG).show()
					source.clear()
					findViewById<EditText>(R.id.month).addTextChangedListener(this)
				}
			}
		})
		// several cities would show as auto completion
		val city_adapter = ArrayAdapter(this,R.layout.simple_dropdown_hint,resources.getStringArray(R.array.city))
		findViewById<AutoCompleteTextView>(R.id.city).setAdapter(city_adapter)
	}
	override fun onResume()
	{
		super.onResume()
		val pizzas = mutableListOf<MutableMap<String, Any>>()
		val cart_item = listOf("Canadian Pizza", "Chicken Caesar", "Hawaiian Pizza", "Smokey Maple Bacon", "Veggie Lover")
		getSharedPreferences("cart",0).all.forEach{ (key, value) -> if(cart_item.contains(key)) pizzas.add(mutableMapOf(key to value.toString())) }
		val list = findViewById<ListView>(R.id.pizza_list)
		list.adapter = ListViewAdapter(this, pizzas)
	}
	// move to summary activity
	fun payment(button: View)
	{
		if((button as MaterialButton).text.equals("pay now"))
		{
			when(findViewById<RadioButton>(findViewById<RadioGroup>(R.id.payment_group).checkedRadioButtonId).text)
			{
				"credit card" ->
				{
					val number = findViewById<EditText>(R.id.card_number).text.ifEmpty { Toast.makeText(this, "empty card number",Toast.LENGTH_LONG).show(); return }
					val month = findViewById<EditText>(R.id.month).text.ifEmpty { Toast.makeText(this, "empty month",Toast.LENGTH_LONG).show(); return }
					val year = findViewById<EditText>(R.id.year).text.ifEmpty { Toast.makeText(this, "empty year",Toast.LENGTH_LONG).show(); return }
					val cvc = findViewById<EditText>(R.id.cvc).text.ifEmpty { Toast.makeText(this, "empty cvc",Toast.LENGTH_LONG).show(); return }
					getSharedPreferences("cart",0).edit().putString("card", mutableListOf(number,month,year,cvc).toString()).apply()
				}
				"debit card" ->
				{
					val number = findViewById<EditText>(R.id.card_number).text.ifEmpty { Toast.makeText(this, "empty card number",Toast.LENGTH_LONG).show(); return }
					val month = findViewById<EditText>(R.id.month).text.ifEmpty { Toast.makeText(this, "empty month",Toast.LENGTH_LONG).show(); return }
					val year = findViewById<EditText>(R.id.year).text.ifEmpty { Toast.makeText(this, "empty year",Toast.LENGTH_LONG).show(); return }
					getSharedPreferences("cart",0).edit().putString("card", mutableListOf(number,month,year).toString()).apply()
				}
			}
			val name = findViewById<EditText>(R.id.name).text.ifEmpty { Toast.makeText(this, "empty name",Toast.LENGTH_LONG).show(); return }
			val phone = findViewById<EditText>(R.id.phone).text.ifEmpty { Toast.makeText(this, "empty phone",Toast.LENGTH_LONG).show(); return }
			val email = findViewById<EditText>(R.id.email).text.ifEmpty { Toast.makeText(this, "empty email",Toast.LENGTH_LONG).show(); return }
			val street = findViewById<EditText>(R.id.street).text.ifEmpty { Toast.makeText(this, "empty street",Toast.LENGTH_LONG).show(); return }
			val city = findViewById<EditText>(R.id.city).text.ifEmpty { Toast.makeText(this, "empty city",Toast.LENGTH_LONG).show(); return }
			val zip = findViewById<EditText>(R.id.zip).text.ifEmpty { Toast.makeText(this, "empty zip code",Toast.LENGTH_LONG).show(); return }
			val note = findViewById<EditText>(R.id.note).text
			getSharedPreferences("cart",0).edit().putString("name", name.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("phone", phone.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("email", email.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("street", street.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("city", city.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("zip", zip.toString()).apply()
			getSharedPreferences("cart",0).edit().putString("note", note.toString()).apply()
			val intent = Intent(this, Summary::class.java)
			startActivity(intent)
		}
	}
}