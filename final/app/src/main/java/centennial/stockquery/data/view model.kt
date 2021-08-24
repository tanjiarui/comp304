package centennial.stockquery.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import centennial.stockquery.data.model.DataBase
import centennial.stockquery.data.model.StockInfo

class DBViewModel(application: Application) : AndroidViewModel(application)
{
	private val database = DataBase.getInstance(application)
	private val repository: Repository
	init {
		val dao = database.stock_dao()
		repository = Repository(dao)
	}
	fun insert(stock: StockInfo)
	{
		repository.insert(stock)
	}
	fun find(symbol: String): StockInfo?
	{
		return repository.find(symbol)
	}
}

lateinit var view_model: DBViewModel