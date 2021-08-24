package centennial.classroom.data

import android.content.Context
import centennial.classroom.data.model.LoggedInUser

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(val context: Context)
{
	fun login(username: Int, password: String): Result<LoggedInUser>
	{
		val faculty = view_model.find_faculty(username)
		val valid_user: LoggedInUser?
		return if(faculty != null && faculty.password == password){
			context.getSharedPreferences("user", 0).edit().putString("last name", faculty.lastname.toString()).apply()
			valid_user = LoggedInUser(faculty.id.toString(), faculty.lastname.toString())
			Result.Success(valid_user)
		} else
			Result.Error(Exception("fail to login"))
	}

	fun logout()
	{
		context.getSharedPreferences("user", 0).all.clear()
	}
}