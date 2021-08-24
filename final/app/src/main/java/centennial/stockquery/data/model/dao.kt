package centennial.stockquery.data.model

import androidx.room.*

@Dao
interface StockDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(element: StockInfo)
	@Query("select * from stock_info where symbol = :symbol")
	fun find(symbol: String): StockInfo?
}