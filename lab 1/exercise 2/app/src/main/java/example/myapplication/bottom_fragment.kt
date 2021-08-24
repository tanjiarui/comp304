package example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BottomFragment : Fragment()
{
	private val message = StringBuilder("bottom fragment")
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
	{
		val view: View = inflater.inflate(R.layout.bottom_fragment, container, false)
		message.append("\nonCreate() Executed")
		val text_view: TextView = view.findViewById(R.id.text_view)
		text_view.text = message
		return view
	}
	override fun onStart()
	{
		super.onStart()
		message.append("\nonStart() Executed")
		val text_view: TextView = requireView().findViewById(R.id.text_view)
		text_view.text = message
	}
	override fun onResume()
	{
		super.onResume()
		message.append("\nonResume() Executed")
		val text_view: TextView = requireView().findViewById(R.id.text_view)
		text_view.text = message
	}
	override fun onPause()
	{
		super.onPause()
		message.append("\nonPause() Executed")
		val text_view: TextView = requireView().findViewById(R.id.text_view)
		text_view.text = message
	}
	override fun onStop()
	{
		super.onStop()
		message.append("\nonStop() Executed")
		val text_view: TextView = requireView().findViewById(R.id.text_view)
		text_view.text = message
	}
	override fun onDestroy()
	{
		super.onDestroy()
		message.append("\nonDestroy() Executed")
		val text_view: TextView = requireView().findViewById(R.id.text_view)
		text_view.text = message
	}
}