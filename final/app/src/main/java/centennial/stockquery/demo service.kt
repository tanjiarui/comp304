package centennial.stockquery

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class DemoService : Service()
{
	override fun onBind(intent: Intent?): IBinder? { return null }
	override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
	{
		Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show()
		val stock = intent.getStringArrayListExtra("stock")
		Toast.makeText(this, stock?.let { String.format(getString(R.string.format), it[0], it[1].toFloat()) }, Toast.LENGTH_LONG).show()
		return START_STICKY
	}
	override fun onDestroy()
	{
		super.onDestroy()
		Toast.makeText(this, "service destroyed", Toast.LENGTH_SHORT).show()
	}
}