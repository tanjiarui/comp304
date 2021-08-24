package centennial.classroom.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Faculty::class, Classroom::class, Student::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase()
{
	abstract fun faculty_dao(): FacultyDao
	abstract fun classroom_dao(): ClassroomDao
	abstract fun student_dao(): StudentDao

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