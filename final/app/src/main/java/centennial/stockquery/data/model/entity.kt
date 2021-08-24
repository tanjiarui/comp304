package centennial.stockquery.data.model

import androidx.room.*

@Entity(tableName = "stock_info")
data class StockInfo(
	@PrimaryKey
	val symbol: String,
	val company: String? = null,
	val quote: Float? = 0f
)