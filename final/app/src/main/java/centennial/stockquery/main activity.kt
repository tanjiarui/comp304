package centennial.stockquery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import centennial.stockquery.data.DBViewModel
import centennial.stockquery.data.model.StockInfo
import centennial.stockquery.data.view_model
import centennial.stockquery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.symbols.setOnCheckedChangeListener { _, _ -> binding.display.isEnabled = true }
	}
	fun button_handler(button: View)
	{
		// initialize the database
		if((button as Button).text.equals(getString(R.string.insert)))
		{
			view_model = ViewModelProvider.AndroidViewModelFactory(application).create(DBViewModel::class.java)
			view_model.insert(StockInfo("TSLA", "Tesla", 665.71f))
			view_model.insert(StockInfo("NIO", "NIO",  38.1f))
			view_model.insert(StockInfo("XPEV", "XPeng",  37.87f))
			view_model.insert(StockInfo("LI", "Li Auto",  28.03f))
		}
		// layout the query
		if(button.text.equals(getString(R.string.display)))
		{
			val id = binding.symbols.checkedRadioButtonId
			val symbol = findViewById<RadioButton>(id).text.toString()
			var stock: StockInfo? = null
			try
			{
				stock = view_model.find(symbol)
			}
			catch (e: Exception)
			{
				Toast.makeText(this, "please insert stocks first", Toast.LENGTH_SHORT).show()
			}
			stock?.let {
				binding.stockInfo.text = String.format(getString(R.string.format), it.company, it.quote)
				val intent = Intent(this, DemoService::class.java)
				intent.putStringArrayListExtra("stock", arrayListOf(it.company, it.quote.toString()))
				startService(intent)
			}
		}
	}
}