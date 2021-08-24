package centennial.classroom.ui.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import centennial.classroom.R
import centennial.classroom.data.LoginDataSource
import centennial.classroom.data.LoginRepository
import centennial.classroom.data.Result

class LoginViewModel(context: Context) : ViewModel()
{

	private val loginRepository: LoginRepository = LoginRepository(LoginDataSource(context))
	private val _loginForm = MutableLiveData<LoginFormState>()
	val loginFormState: LiveData<LoginFormState> = _loginForm

	private val _loginResult = MutableLiveData<LoginResult>()
	val loginResult: LiveData<LoginResult> = _loginResult

	fun login(username: Int, password: String) {
		// can be launched in a separate asynchronous job
		val result = loginRepository.login(username, password)

		if (result is Result.Success) {
			_loginResult.value =
				LoginResult(success = result.data?.let { LoggedInUserView(displayName = it.displayName) })
		} else {
			_loginResult.value = LoginResult(error = R.string.login_failed)
		}
	}

	fun logout(){
		_loginForm.value = LoginFormState(isDataValid = false)
		loginRepository.logout()
	}

	fun loginDataChanged(username: String, password: String) {
		if (!isUserNameValid(username)) {
			_loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
		} else if (!isPasswordValid(password)) {
			_loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
		} else {
			_loginForm.value = LoginFormState(isDataValid = true)
		}
	}

	// A placeholder username validation check
	private fun isUserNameValid(username: String): Boolean {
		return if (username.contains('@')) {
			Patterns.EMAIL_ADDRESS.matcher(username).matches()
		} else {
			username.isNotBlank()
		}
	}

	// A placeholder password validation check
	private fun isPasswordValid(password: String): Boolean {
		return password.length > 5
	}
}