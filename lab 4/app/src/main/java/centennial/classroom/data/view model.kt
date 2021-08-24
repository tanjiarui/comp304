package centennial.classroom.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import centennial.classroom.data.model.Classroom
import centennial.classroom.data.model.DataBase
import centennial.classroom.data.model.Faculty
import centennial.classroom.data.model.Student

class DBViewModel(application: Application) : AndroidViewModel(application)
{
	val database = DataBase.getInstance(application)
	private val repository: Repository
	init {
		val faculty_dao = database.faculty_dao()
		val classroom_dao = database.classroom_dao()
		val student_dao = database.student_dao()
		repository = Repository(faculty_dao, classroom_dao, student_dao)
	}
	fun insert_faculty(faculty: Faculty)
	{
		repository.insert_faculty(faculty)
	}
	fun find_all_faculty(): MutableList<Faculty>?
	{
		return repository.find_all_faculty()
	}
	fun find_faculty(id: Int): Faculty?
	{
		return repository.find_faculty(id)
	}
	fun update_faculty(faculty: Faculty)
	{
		return repository.update_faculty(faculty)
	}
	fun delete_faculty(faculty: Faculty)
	{
		repository.delete_faculty(faculty)
	}
	fun delete_all_faculty()
	{
		repository.delete_all_faculty()
	}
	fun insert_classroom(classroom: Classroom)
	{
		repository.insert_classroom(classroom)
	}
	fun find_classroom(id: Int): Classroom?
	{
		return repository.find_classroom(id)
	}
	fun delete_all_classroom()
	{
		repository.delete_all_classroom()
	}
	fun insert_student(student: Student)
	{
		repository.insert_student(student)
	}
	fun find_all_student(): MutableList<Student>?
	{
		return repository.find_all_student()
	}
	fun find_student(id: Int): Student?
	{
		return repository.find_student(id)
	}
	fun update_student(student: Student)
	{
		repository.update_student(student)
	}
	fun delete_student(student: Student)
	{
		repository.delete_student(student)
	}
	fun delete_all_student()
	{
		repository.delete_all_student()
	}
}
lateinit var view_model: DBViewModel