package centennial.classroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import centennial.classroom.data.model.Classroom
import centennial.classroom.data.model.Faculty
import centennial.classroom.data.model.Student
import centennial.classroom.data.view_model
import centennial.classroom.databinding.EditActivityBinding

class EditActivity : AppCompatActivity()
{
	private lateinit var binding: EditActivityBinding
	private lateinit var id: String
	private lateinit var firstname: EditText
	private lateinit var lastname: EditText
	private lateinit var department: EditText
	private lateinit var faculty: EditText
	private lateinit var classroom_id: EditText
	private lateinit var floor: TextView
	private lateinit var ac: TextView

	// the update button is clickable only when all inputs are ready
	private fun input_changed(firstname: String, lastname: String, department: String, faculty: String, classroom: String)
	{
		binding.update.isEnabled = firstname.isNotEmpty() && lastname.isNotEmpty() && department.isNotEmpty() && faculty.isNotEmpty() && classroom.isNotEmpty()
	}
	private fun load_query(id: Int)
	{
		val student: Student? = view_model.find_student(id)
		val classroom: Classroom? = student?.let { view_model.find_classroom(it.classroom) }
		student?.let {
			firstname = binding.firstname
			firstname.setText(it.firstname)
			lastname = binding.lastname
			lastname.setText(it.lastname)
			department = binding.department
			department.setText(it.department)
			faculty = binding.faculty
			faculty.setText(it.faculty_id.toString())
			classroom_id = binding.classroom
			classroom_id.setText(it.classroom.toString())
		}
		classroom?.let {
			floor = binding.floor
			floor.text = String.format(getString(R.string.floor), it.floor)
			ac = binding.ac
			ac.text = String.format(getString(R.string.ac), it.air_conditioned)
		}
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = EditActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)
		this.title = "edit student"
		id = intent.getStringExtra("id").toString()
		binding.student.text = id
		load_query(id.toInt())
		firstname.afterTextChanged {
			input_changed(
				firstname.text.toString(),
				lastname.text.toString(),
				department.text.toString(),
				faculty.text.toString(),
				classroom_id.text.toString()
			)
		}
		lastname.afterTextChanged {
			input_changed(
				firstname.text.toString(),
				lastname.text.toString(),
				department.text.toString(),
				faculty.text.toString(),
				classroom_id.text.toString()
			)
		}
		department.afterTextChanged {
			input_changed(
				firstname.text.toString(),
				lastname.text.toString(),
				department.text.toString(),
				faculty.text.toString(),
				classroom_id.text.toString()
			)
		}
		faculty.afterTextChanged {
			input_changed(
				firstname.text.toString(),
				lastname.text.toString(),
				department.text.toString(),
				faculty.text.toString(),
				classroom_id.text.toString()
			)
		}
		classroom_id.afterTextChanged {
			input_changed(
				firstname.text.toString(),
				lastname.text.toString(),
				department.text.toString(),
				faculty.text.toString(),
				classroom_id.text.toString()
			)
		}
	}
	// bind to the update and delete button
	fun update_delete(button: View)
	{
		if((button as Button).text.equals("update"))
		{
			val valid_faculty: Faculty? = view_model.find_faculty(faculty.text.toString().toInt())
			val valid_classroom: Classroom? = view_model.find_classroom(classroom_id.text.toString().toInt())
			if(valid_faculty != null && valid_classroom != null)
			{
				view_model.update_student(Student(id.toInt(), firstname.text.toString(), lastname.text.toString(), department.text.toString(), faculty.text.toString().toInt(), classroom_id.text.toString().toInt()))
				finish()
			}
			else
				Toast.makeText(this, "no such professor or classroom", Toast.LENGTH_SHORT).show()
		}
		if(button.text.equals("delete"))
		{
			val student: Student? = view_model.find_student(id.toInt())
			if (student != null)
				view_model.delete_student(student)
			finish()
		}
	}
}