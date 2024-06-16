package app.aplicacion.basededatos

import UserAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.basededatos.data.User
import app.aplicacion.basededatos.data.UserViewModel
import app.aplicacion.basededatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model:UserViewModel
    private lateinit var adapter: UserAdapter;
    private  lateinit var recyclerView: RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        adapter= UserAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        model=ViewModelProvider(this).get(UserViewModel::class.java)


        model.lista.observe(this,Observer{users -> users?.let { adapter.setUsers(it) }})


        binding.btnAdd.setOnClickListener {
            insertUser()
        }
        binding.btngetUser.setOnClickListener {
        }

    }
    private fun insertUser(){
        val user=User(0,"Darwin",3)
        model.insertUser(user)
        Toast.makeText(this,"registro insertado con exito",Toast.LENGTH_SHORT).show()
    }

}