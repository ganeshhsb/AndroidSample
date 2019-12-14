package com.nova.coroutinesample.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.replace(fragmentManager: FragmentManager, layoutId: Int) {
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(layoutId, this, this.javaClass.simpleName)
    transaction.commitAllowingStateLoss()
}