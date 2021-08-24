package centennial.stockquery.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StockInfo::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase()
{
	abstract fun stock_dao(): StockDao

	companion object {
		private var instance: DataBase? = null

		@Synchronized
		fun getInstance(context: Context): DataBase
		{
			if (instance == null)
			{
				//create database object
				instance = Room.inMemoryDatabaseBuilder(context.applicationContext, DataBase::class.java).allowMainThreadQueries().fallbackToDestructiveMigration().build()
			}
			return instance as DataBase
		}
	}
}