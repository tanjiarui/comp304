package example.centenpizza

import android.app.Activity
import android.app.Application

class MyApplication : Application()
{
	private val activities = mutableListOf<Activity>()
	fun add_activity(activity: Activity)
	{
		activities.add(activity)
	}
	override fun onTerminate()
	{
		super.onTerminate()
		for(activity in this.activities)
			activity.finish()
	}
}

val my_app = MyApplication()