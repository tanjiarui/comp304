package centennial.classroom.data.model

import androidx.room.*

@Entity(tableName = "faculty")
data class Faculty(
	@PrimaryKey
	val id: Int = 0,
	var firstname: String? = null,
	var lastname: String? = null,
	var department: String? = null,
	var password: String? = null
)
{
	@Ignore
	constructor() : this(0)
}

@Entity(tableName = "classroom")
data class Classroom(
	@PrimaryKey
	val id: Int = 0,
	val student_id: Int = 0,
	val faculty_id: Int = 0,
	val floor: Int = 0,
	val air_conditioned: Boolean
)

@Entity(tableName = "student")
data class Student(
	@PrimaryKey
	val id: Int = 0,
	var firstname: String? = null,
	var lastname: String? = null,
	var department: String? = null,
	val faculty_id: Int = 0,
	val classroom: Int = 0,
)
{
	@Ignore
	constructor() : this(0)
}