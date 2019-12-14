package com.nova.coroutinesample.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nova.coroutinesample.databinding.FragmentUserListBinding
import com.nova.coroutinesample.user.model.User
import com.nova.coroutinesample.user.viewmodel.UserListModel
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_user_list.*
import java.util.concurrent.Executors
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserListFragment : Fragment(), UserCreationDialog.UserCreationDialogListener,
    UserListAdapter.UserItemClickListener {
    private lateinit var activityScope: CoroutineDispatcher
    private lateinit var userListModel: UserListModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentUserListBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityScope = Executors.newCachedThreadPool().asCoroutineDispatcher()
        userListModel = ViewModelProviders.of(this).get(UserListModel::class.java)

        fab.setOnClickListener { view ->
            val fragment = UserCreationDialog()
            fragment.show(childFragmentManager, "UserCreationDialog")
        }
        initViews()
    }

    private fun initViews() {
        val adapter = UserListAdapter()
        adapter.listener = this
        adapter.userList = mutableListOf()
        user_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        user_list.adapter = adapter
        // addRecyclerViewSwipeListener()
        userListModel.getUsers().observe(viewLifecycleOwner, Observer {
            adapter.onNewData(it)
        })

        userListModel.singleEvent.observe(viewLifecycleOwner, Observer {
            this@UserListFragment.view?.let { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        })
    }

    override fun onUserCreation(user: User?) {
        GlobalScope.launch(activityScope) {
            user?.let { userListModel.insert(it) }
        }

    }

    override fun onUserItemClick(user: User) {
        val fragment = UserCreationDialog()
        fragment.user = user
        fragment.show(childFragmentManager, "UserCreationDialog")
    }

    private fun addRecyclerViewSwipeListener() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            /*ItemTouchHelper.UP or ItemTouchHelper.DOWN*/0,
            /*ItemTouchHelper.LEFT or*/ ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //awesome code when user grabs recycler card to reorder
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //awesome code when swiping right to remove recycler card and delete SQLite data
                val userList = (user_list.adapter as UserListAdapter).userList
                val user: User? = userList?.get(viewHolder.adapterPosition)
                user?.let {
                    if (direction == ItemTouchHelper.LEFT) {
                        // DO Action for Left
                        showUserCreationDialog(user)

                    } else if (direction == ItemTouchHelper.RIGHT) {
                        // DO Action for Right
                        GlobalScope.launch(activityScope) { userListModel.delete(user) }
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(user_list)
    }

    fun showUserCreationDialog(user: User?) {
        val fragment = UserCreationDialog()
        user?.let { fragment.user = user }
        fragment.show(childFragmentManager, "UserCreationDialog")
    }
}

class UserListDiffCallback(private var newPersons: List<User>, private var oldPersons: List<User>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldPersons.size
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].name === newPersons[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition] == newPersons[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}