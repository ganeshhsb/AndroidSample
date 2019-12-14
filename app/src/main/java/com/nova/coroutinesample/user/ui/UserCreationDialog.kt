package com.nova.coroutinesample.user.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.nova.coroutinesample.R
import com.nova.coroutinesample.user.model.User
import com.nova.coroutinesample.databinding.FragmentUserCreationBinding
import kotlinx.android.synthetic.main.fragment_user_creation.*

class UserCreationDialog : DialogFragment() {
    interface UserCreationDialogListener {
        fun onUserCreation(user: User? = null)
    }

    private var listener: UserCreationDialogListener? = null
    var user: User =
        User("", "")
    private val emptyUser = User("", "")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? UserCreationDialogListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserCreationBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_user_creation, container, false
            )
        binding.user = user
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        fragment_toolbar.inflateMenu(R.menu.menu_user_creation_fragment)
        fragment_toolbar.title = getString(R.string.add_user)
        fragment_toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_close_24px)
        fragment_toolbar.setNavigationOnClickListener {
            listener?.onUserCreation(null)
            dismiss()
        }
        fragment_toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_done -> {
                    val returnValue = if (user == emptyUser) {
                        null
                    } else {
                        user
                    }

                    listener?.onUserCreation(returnValue)
                    dismiss()
                }
            }
            true
        }
    }
}