package com.nova.coroutinesample.user.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nova.coroutinesample.R
import com.nova.coroutinesample.databinding.UserItemBinding
import com.nova.coroutinesample.user.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    interface UserItemClickListener {
        fun onUserItemClick(user: User)
    }

    var listener: UserItemClickListener? = null
    var userList: MutableList<User>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        userList?.let { users ->
            holder.bind(users[position])
            holder.userItemBinding.itemClickListener = listener
        }

    }

    class UserViewHolder(val userItemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(userItemBinding.root) {
        fun bind(user: User) {
            userItemBinding.user = user
            userItemBinding.executePendingBindings()
        }
    }

    fun onNewData(newData: MutableList<User>) {
        val diffResult = DiffUtil.calculateDiff(
            UserListDiffCallback(
                userList!!,
                newData
            )
        )
        this.userList?.clear()
        this.userList?.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}