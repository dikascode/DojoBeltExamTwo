package com.dikascode.beltexamtwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.beltexamtwo.databinding.UserDatabaseItemCardBinding
import com.dikascode.beltexamtwo.model.UserLocalDataModel

class UserLocalStorageAdapter : RecyclerView.Adapter<UserLocalStorageAdapter.StudentViewHolder>() {
    private var studentList = ArrayList<UserLocalDataModel>()
    private var setOnClickItem: ((UserLocalDataModel) -> Unit)? = null
    private var setOnClickDeleteItem: ((UserLocalDataModel) -> Unit)? = null

    fun addItems(items: ArrayList<UserLocalDataModel>){
        this.studentList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback: (UserLocalDataModel) -> Unit) {
        this.setOnClickDeleteItem = callback
    }

    fun setOnClickItem(callback: (UserLocalDataModel) -> Unit) {
        this.setOnClickItem = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = StudentViewHolder (
        UserDatabaseItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserLocalStorageAdapter.StudentViewHolder, position: Int) {
        val studentData = studentList[position]
        holder.bind(studentData)
        holder.itemView.setOnClickListener { setOnClickItem?.invoke(studentData) }
        holder.btnButton.setOnClickListener { setOnClickDeleteItem?.invoke(studentData) }
    }

    override fun getItemCount(): Int = studentList.size

    inner class StudentViewHolder(private val binding: UserDatabaseItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var btnButton = binding.btnDelete

        fun bind(student: UserLocalDataModel){
            binding.tvName.text = student.fName
            binding.tvLastName.text = student.lName
            binding.tvEmail.text = student.email
            binding.tvCity.text = student.city
            binding.tvState.text = student.state
        }
    }
}