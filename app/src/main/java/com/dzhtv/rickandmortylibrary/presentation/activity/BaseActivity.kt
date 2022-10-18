package com.dzhtv.rickandmortylibrary.presentation.activity

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.contract.BaseActivityView
import com.dzhtv.rickandmortylibrary.presentation.fragment.BaseFragment

abstract class BaseActivity : AppCompatActivity(), BaseActivityView {

    fun replaceFragment(fragment: BaseFragment) {
        if (hasVisibleFragment(fragment::class.java.simpleName)) {
            removeFragmentFromStack(fragment::class.java.simpleName)
        }
        replaceFragmentFromBackStack(fragment)
    }

    fun replaceFullscreenFragment(fragment: BaseFragment) {

    }

    fun hideFragment(fragment: BaseFragment) {
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(fragment)
        }
    }

    private fun replaceFragmentFromBackStack(
        fragment: BaseFragment,
        @IdRes fragmentContainer: Int = R.id.fragment_container
    ) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    fun hasVisibleFragment(fragmentName: String): Boolean {
        return supportFragmentManager.findFragmentByTag(fragmentName)?.isVisible
            ?: false
    }

    private fun removeFragmentFromStack(tag: String, allowStateLoss: Boolean = false): Boolean {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        fragment?.let {
            supportFragmentManager.popBackStackImmediate()

//            when {
//                !supportFragmentManager.isStateSaved -> transaction.commit()
//                allowStateLoss -> transaction.commitAllowingStateLoss()
//            }
            return true
        }
        return false
    }

    // new solutions
    fun showFragment(
        fragment: BaseFragment,
        @IdRes fragmentContainer: Int = R.id.fragment_container
    ) {
        val addedFragment =
            supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName)
        if (addedFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(fragmentContainer, addedFragment, addedFragment::class.java.simpleName)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(fragmentContainer, fragment, fragment::class.java.simpleName)
                .addToBackStack(fragment::class.java.simpleName)
                .commit()
        }
    }
}