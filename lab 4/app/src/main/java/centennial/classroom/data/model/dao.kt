package centennial.classroom.data.model

import androidx.room.*

@Dao
interface FacultyDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(element: Faculty)
	@Query("select * from faculty")
	fun find_all(): MutableList<Faculty>?
	@Query("select * from faculty where id = :username")
	fun find(username: Int): Faculty?
	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(element: Faculty)
	@Delete
	fun delete(element: Faculty)
	@Query("delete from faculty")
	fun delete_all()
}

@Dao
interface ClassroomDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(element: Classroom)
	@Query("select * from classroom where id = :room_id")
	fun find(room_id: Int): Classroom?
	@Query("delete from classroom")
	fun delete_all()
}

@Dao
interface StudentDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(element: Student)
	@Query("select * from student")
	fun find_all(): MutableList<Student>?
	@Query("select * from student where id = :student_id")
	fun find(student_id: Int): Student?
	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(element: Student)
	@Delete
	fun delete(element: Student)
	@Query("delete from student")
	fun delete_all()
}