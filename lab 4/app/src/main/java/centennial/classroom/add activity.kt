package centennial.classroom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import centennial.classroom.data.model.Classroom
import centennial.classroom.data.model.Faculty
import centennial.classroom.data.model.Student
import centennial.classroom.data.view_model
import centennial.classroom.databinding.AddActivityBinding

class AddActivity : AppCompatActivity()
{
	private lateinit var binding: AddActivityBinding

	// the add button is clickable only when all inputs are ready
	private fun input_changed(id: String, firstname: String, lastname: String, department: String, faculty: String, classroom: String)
	{
		binding.add.isEnabled = id.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && department.isNotEmpty() && faculty.isNotEmpty() && classroom.isNotEmpty()
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = AddActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)
		this.title = "add student"
		binding.add.isEnabled = false
		binding.student.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
		binding.firstname.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
		binding.lastname.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
		binding.department.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
		binding.faculty.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
		binding.classroom.afterTextChanged {
			input_changed(
				binding.student.text.toString(),
				binding.firstname.text.toString(),
				binding.lastname.text.toString(),
				binding.department.text.toString(),
				binding.faculty.text.toString(),
				binding.classroom.text.toString()
			)
		}
	}
	// insert a record
	fun add_student(button: View)
	{
		if((button as Button).text.equals("add"))
		{
			val id = binding.student.text.toString().toInt()
			val firstname = binding.firstname.text.toString()
			val lastname = binding.lastname.text.toString()
			val department = binding.department.text.toString()
			val faculty = binding.faculty.text.toString().toInt()
			val classroom = binding.classroom.text.toString().toInt()
			val valid_faculty: Faculty? = view_model.find_faculty(faculty)
			val valid_classroom: Classroom? = view_model.find_classroom(classroom)
			if(valid_faculty != null && valid_classroom != null)
			{
				view_model.insert_student(Student(id, firstname, lastname, department, faculty, classroom))
				finish()
			}
			else
				Toast.makeText(this, "no such professor or classroom", Toast.LENGTH_SHORT).show()
		}
	}
}