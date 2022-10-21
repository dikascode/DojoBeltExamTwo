package com.dikascode.beltexamtwo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.beltexamtwo.adapter.UsersAdapter
import com.dikascode.beltexamtwo.base_interface.BaseView
import com.dikascode.beltexamtwo.controller.UserDataController
import com.dikascode.beltexamtwo.databinding.ActivityMain2Binding
import com.dikascode.beltexamtwo.model.UserData
import com.dikascode.beltexamtwo.model.UserDataItem

class MainActivity2 : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMain2Binding

    private lateinit var usersListRecyclerView: RecyclerView
    lateinit var userAdapter: UsersAdapter
    private val userController by lazy {  UserDataController(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        getUserDataFromApi()
        setContentView(binding.root)
    }

    private fun getUserDataFromApi() {
        return userController.getUserData()
    }

    override fun showToast(message: Int) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun initRecyclerView(usersList: UserData) {
        super.initRecyclerView(usersList)
        usersListRecyclerView = binding.recyclerView
        usersListRecyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UsersAdapter(usersList)
        usersListRecyclerView.adapter = userAdapter
        userAdapter.setOnClickItem { userData ->
            launchIntent(userData)
        }
    }


    override fun showLoading(state: Boolean) {
        super.showLoading(state)
        if(state) {
            binding.showLoading.visibility = View.VISIBLE
        }else{
            binding.showLoading.visibility = View.GONE
        }
    }

    private fun launchIntent(userData:UserDataItem) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userData", userData)
        startActivity(intent)
    }
}