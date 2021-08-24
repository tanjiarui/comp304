package centennial.stockquery.data

import centennial.stockquery.data.model.*

class Repository(private val stock_dao: StockDao)
{
	fun insert(stock: StockInfo)
	{
		stock_dao.insert(stock)
	}
	fun find(symbol: String): StockInfo?
	{
		return stock_dao.find(symbol)
	}
}