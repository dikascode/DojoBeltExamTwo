package com.dikascode.beltexamtwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.beltexamtwo.databinding.UserDataCardBinding
import com.dikascode.beltexamtwo.model.UserDataItem

class UsersAdapter(private val usersList:ArrayList<UserDataItem>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var setOnClickItem: ((UserDataItem) -> Unit)? = null

    fun setOnClickItem(callback: (UserDataItem) -> Unit) {
        this.setOnClickItem = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = UserViewHolder(
        UserDataCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UsersAdapter.UserViewHolder, position: Int) {
        val userData = usersList[position]
        holder.bind(userData)
        holder.itemView.setOnClickListener { setOnClickItem?.invoke(userData) }
    }

    override fun getItemCount(): Int = usersList.size

    inner class UserViewHolder(private val binding: UserDataCardBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserDataItem) {
            binding.tvId.text = "Id: ${user.id}"
            binding.tvName.text = "Firstname: ${user.firstName}"
            binding.tvLastName.text = "Lastname: ${user.lastName}"
            binding.tvEmail.text = "Email: ${user.email}"
            binding.tvCity.text = "City: ${user.address.city}"
            binding.tvState.text = "State: ${user.address.state}"
        }
    }
}