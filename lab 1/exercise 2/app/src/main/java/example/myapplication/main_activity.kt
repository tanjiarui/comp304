package example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		Toast.makeText(this,"onCreate() Executed For Fragments",Toast.LENGTH_SHORT).show()
		Toast.makeText(this,"onStart() Executed For Fragments",Toast.LENGTH_SHORT).show()
	}
}