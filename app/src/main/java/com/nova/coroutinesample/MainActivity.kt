package com.nova.coroutinesample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nova.coroutinesample.common.replace
import com.nova.coroutinesample.user.ui.UserListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUserListingFragment()
    }

    private fun addUserListingFragment() {
        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            val fragment = UserListFragment()
            fragment.replace(supportFragmentManager, R.id.container)
        }
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
//                val fragment = UserCreationDialog()
//                fragment.show(supportFragmentManager, "UserCreationDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
