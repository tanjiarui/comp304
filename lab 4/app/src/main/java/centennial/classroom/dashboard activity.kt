package centennial.classroom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import centennial.classroom.data.view_model
import centennial.classroom.ui.login.LoginViewModel
import centennial.classroom.ui.login.LoginViewModelFactory

class RecyclerViewAdapter(private val context: Context?, private val list: MutableList<Int>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
	{
		val id: TextView = itemView.findViewById(R.id.student_id)
	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
	{
		val view:View = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
		return ViewHolder(view)
	}
	override fun onBindViewHolder(holder: ViewHolder, position: Int)
	{
		holder.id.text = list[position].toString()
		holder.itemView.setOnClickListener{
			val id = it.findViewById<TextView>(R.id.student_id).text
			val intent = Intent(context, EditActivity::class.java)
			intent.putExtra("id", id)
			context?.startActivity(intent)
		}
	}
	override fun getItemCount() : Int { return list.size }
}

class Dashboard : AppCompatActivity()
{
	private lateinit var login_view: LoginViewModel

	// setup the recycler view
	private fun recyclerview_syn()
	{
		val id_list = mutableListOf<Int>()
		view_model.find_all_student()?.forEach{ item -> id_list.add(item.id)}
		val recycler_view = findViewById<RecyclerView>(R.id.student_table)
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.adapter = RecyclerViewAdapter(this, id_list)
	}
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dashboard_activity)
		login_view = ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)
		val name = getSharedPreferences("user", 0).getString("last name", null)
		this.title = "welcome! Professor $name"
		recyclerview_syn()
	}
	override fun onResume()
	{
		super.onResume()
		recyclerview_syn()
	}
	override fun onCreateOptionsMenu(menu: Menu?): Boolean
	{
		menuInflater.inflate(R.menu.main, menu)
		return super.onCreateOptionsMenu(menu)
	}
	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		login_view.logout()
		Toast.makeText(this, "successfully logged out", Toast.LENGTH_SHORT).show()
		finish()
		return super.onOptionsItemSelected(item)
	}
	// move to the add activity
	fun click_to_add(button: View)
	{
		if((button as Button).text.equals("add student"))
		{
			val intent = Intent(this, AddActivity::class.java)
			startActivity(intent)
		}
	}
}