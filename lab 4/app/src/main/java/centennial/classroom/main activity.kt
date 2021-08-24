package centennial.classroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import centennial.classroom.data.DBViewModel
import centennial.classroom.data.model.*
import centennial.classroom.data.view_model

class MainActivity : AppCompatActivity()
{
	// initialize the database
	fun db_init()
	{
		view_model = ViewModelProvider(this).get(DBViewModel::class.java)
		view_model.insert_faculty(Faculty(0, "Vinayagathas", "Vaithilingam", "ICET", "222222"))
		view_model.insert_faculty(Faculty(1, "George", "Kougioumtzoglou", "ICET", "444444"))
		view_model.insert_classroom(Classroom(201, 10, 0, 2, false))
		view_model.insert_classroom(Classroom(202, 11, 0, 2, false))
		view_model.insert_classroom(Classroom(301, 12, 1, 3, true))
		view_model.insert_classroom(Classroom(302, 13, 1, 3, true))
		view_model.insert_student(Student(10, "Abdullah", "Sheikh", "ICET", 0, 201))
		view_model.insert_student(Student(11, "Amrit", "Sapkota", "ICET", 0, 202))
		view_model.insert_student(Student(12, "Ayman", "Sbeiti", "ICET", 1, 301))
		view_model.insert_student(Student(13, "Bernadette", "Alvarado", "ICET", 1, 302))
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main_activity)
		// clean the preference file
		getSharedPreferences("user", 0).all.clear()
		db_init()
	}
	fun log_in(button: View)
	{
		if((button as Button).text.equals("enter"))
		{
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
		}
	}
}