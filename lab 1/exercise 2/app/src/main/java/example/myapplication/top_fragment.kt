package example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class TopFragment : Fragment()
{
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
	{
		val view: View = inflater.inflate(R.layout.top_fragment, container, false)
		val menu = arrayOf("AIActivity", "VRActivity")
		val list_view: ListView = view.findViewById(R.id.list)
		val adapter = ArrayAdapter(
			requireActivity(),
			android.R.layout.simple_list_item_1,
			menu
		)
		list_view.onItemClickListener = OnItemClickListener{ _, _, arg2, _ ->
				if (arg2 == 0)
				{
					val intent = Intent(view.context, AIActivity::class.java)
					startActivity(intent)
				}
				else
				{
					val intent = Intent(view.context, VRActivity::class.java)
					startActivity(intent)
				}
		}
		list_view.adapter = adapter;
		return view
	}
}