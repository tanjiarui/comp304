package example.centenpizza

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox

class ChooseTypeActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.choose_type)
		my_app.add_activity(this)
	}
	override fun onCreateOptionsMenu(menu: Menu?): Boolean
	{
		menuInflater.inflate(R.menu.options_menu, menu)
		return true
	}
	lateinit var pizza_type : String
	// handling different select events
	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		pizza_type = item.title as String
		Toast.makeText(this, "selected item " + item.title, Toast.LENGTH_SHORT).show()
		val cart = findViewById<CheckBox>(R.id.cart)
		cart.visibility = View.VISIBLE
		cart.isChecked = getSharedPreferences("cart",0).contains(pizza_type)
		return when(item.itemId)
		{
			R.id.CanadianPizza ->
			{
				findViewById<TextView>(R.id.ingredient).text = getString(R.string.canadian_ingredient)
				findViewById<ImageView>(R.id.pizza_image).setImageResource(R.drawable.canadian_pizza)
				true
			}
			R.id.ChickenCaesar ->
			{
				findViewById<TextView>(R.id.ingredient).text = getString(R.string.chicken_ingredient)
				findViewById<ImageView>(R.id.pizza_image).setImageResource(R.drawable.chicken_caesar)
				true
			}
			R.id.HawaiianPizza ->
			{
				findViewById<TextView>(R.id.ingredient).text = getString(R.string.hawaiian_ingredient)
				findViewById<ImageView>(R.id.pizza_image).setImageResource(R.drawable.hawaiian_pizza)
				true
			}
			R.id.SmokeyMapleBacon ->
			{
				findViewById<TextView>(R.id.ingredient).text = getString(R.string.smokey_ingredient)
				findViewById<ImageView>(R.id.pizza_image).setImageResource(R.drawable.smokey_maple_bacon)
				true
			}
			R.id.VeggieLover ->
			{
				findViewById<TextView>(R.id.ingredient).text = getString(R.string.veggie_ingredient)
				findViewById<ImageView>(R.id.pizza_image).setImageResource(R.drawable.veggie_lover)
				true
			}
			R.id.ClearCart ->
			{
				cart.visibility = View.INVISIBLE
				getSharedPreferences("cart",0).edit().clear().apply()
				Toast.makeText(this, "your cart has been cleared",Toast.LENGTH_LONG).show()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}
	// keep the checkbox state identical with the cart
	fun syn_to_cart(checkbox: View)
	{
		if((checkbox as MaterialCheckBox).isChecked)
		{
			getSharedPreferences("cart",0).edit().putString(pizza_type,"in cart").apply()
			Toast.makeText(this, "$pizza_type was added to your cart",Toast.LENGTH_LONG).show()
		}
		else
		{
			getSharedPreferences("cart",0).edit().remove(pizza_type).apply()
			Toast.makeText(this,"$pizza_type was removed from your cart",Toast.LENGTH_LONG).show()
		}
	}
	// move to cart detail activity
	fun go_to_cart(button: View)
	{
		if((button as MaterialButton).text.equals("my cart"))
		{
			if(getSharedPreferences("cart",0).contains("Canadian Pizza") or getSharedPreferences("cart",0).contains("Chicken Caesar") or getSharedPreferences("cart",0).contains("Hawaiian Pizza") or getSharedPreferences("cart",0).contains("Smokey Maple Bacon") or getSharedPreferences("cart",0).contains("Veggie Lover"))
			{
				val intent = Intent(this, CartDetail::class.java)
				startActivity(intent)
			}
			else
				Toast.makeText(this,"please select at least one item",Toast.LENGTH_LONG).show()
		}
	}
}