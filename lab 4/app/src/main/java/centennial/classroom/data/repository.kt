package centennial.classroom.data

import centennial.classroom.data.model.*

class Repository(private val faculty_dao: FacultyDao, private val classroom_dao: ClassroomDao, private val student_dao: StudentDao)
{
	fun insert_faculty(faculty: Faculty)
	{
		faculty_dao.insert(faculty)
	}
	fun find_all_faculty(): MutableList<Faculty>?
	{
		return faculty_dao.find_all()
	}
	fun find_faculty(id: Int): Faculty?
	{
		return faculty_dao.find(id)
	}
	fun update_faculty(faculty: Faculty)
	{
		faculty_dao.update(faculty)
	}
	fun delete_faculty(faculty: Faculty)
	{
		faculty_dao.delete(faculty)
	}
	fun delete_all_faculty()
	{
		faculty_dao.delete_all()
	}
	fun insert_classroom(classroom: Classroom)
	{
		classroom_dao.insert(classroom)
	}
	fun find_classroom(id: Int): Classroom?
	{
		return classroom_dao.find(id)
	}
	fun delete_all_classroom()
	{
		classroom_dao.delete_all()
	}
	fun insert_student(student: Student)
	{
		student_dao.insert(student)
	}
	fun find_all_student(): MutableList<Student>?
	{
		return student_dao.find_all()
	}
	fun find_student(id: Int): Student?
	{
		return student_dao.find(id)
	}
	fun update_student(student: Student)
	{
		student_dao.update(student)
	}
	fun delete_student(student: Student)
	{
		student_dao.delete(student)
	}
	fun delete_all_student()
	{
		student_dao.delete_all()
	}
}