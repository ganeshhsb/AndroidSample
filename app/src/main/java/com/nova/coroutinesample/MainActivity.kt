package com.nova.coroutinesample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.nova.coroutinesample.user.viewmodel.MainActivityModel
import com.nova.coroutinesample.user.model.User
import com.nova.coroutinesample.user.ui.UserCreationDialog
import com.nova.coroutinesample.user.ui.UserListAdapter
import kotlinx.coroutines.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity(), UserCreationDialog.UserCreationDialogListener,
    UserListAdapter.UserItemClickListener {

    private lateinit var activityScope: CoroutineDispatcher
    private lateinit var mainActivityModel: MainActivityModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        activityScope = Executors.newCachedThreadPool().asCoroutineDispatcher()
        mainActivityModel = ViewModelProviders.of(this).get(MainActivityModel::class.java)

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            val fragment = UserCreationDialog()
            fragment.show(supportFragmentManager, "UserCreationDialog")
        }
        initViews()
    }

    private fun initViews() {
        val adapter = UserListAdapter()
        adapter.listener = this
        adapter.userList = mutableListOf()
        user_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        user_list.adapter = adapter

        mainActivityModel.getUsers().observe(this, Observer {
            adapter.onNewData(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val fragment = UserCreationDialog()
                fragment.show(supportFragmentManager, "UserCreationDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onUserCreation(user: User?) {
        GlobalScope.launch(activityScope) {
            user?.let { mainActivityModel.insert(it) }
        }

    }

    override fun onUserItemClick(user: User) {
        val fragment = UserCreationDialog()
        fragment.user = user
        fragment.show(supportFragmentManager, "UserCreationDialog")
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
