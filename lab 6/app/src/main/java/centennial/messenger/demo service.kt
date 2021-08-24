package centennial.messenger

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class DemoService : Service()
{
	companion object { const val INFO_INTENT = "info update" }
	override fun onBind(intent: Intent?): IBinder? { return null }
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
	{
		Toast.makeText(this, "service started", Toast.LENGTH_LONG).show()
		val broadcast = Intent()
		broadcast.action = INFO_INTENT
		broadcast.putExtra(INFO_INTENT, "this is a demo service")
		this.sendBroadcast(broadcast)
		return START_STICKY
	}
	override fun onDestroy()
	{
		super.onDestroy()
		Toast.makeText(this, "service destroyed", Toast.LENGTH_LONG).show()
	}
}