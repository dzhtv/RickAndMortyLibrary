package com.dzhtv.rickandmortylibrary.presentation.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.dzhtv.rickandmortylibrary.R

abstract class BaseActivity : AppCompatActivity() {

    fun replaceFragment(fragment: BaseFragment) {
        replaceFragmentFromBackStack(fragment)
    }

    fun hideFragment(fragment: BaseFragment) {
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(fragment)
        }
    }

    fun showFragment(fragment: BaseFragment, @IdRes fragmentContainer: Int = R.id.container_main) {
        val ft = supportFragmentManager.beginTransaction()
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.add(fragmentContainer, fragment, fragment::class.java.simpleName)
        }
        ft.commit()
    }

    private fun replaceFragmentFromBackStack(fragment: BaseFragment, @IdRes fragmentContainer: Int = R.id.container_main) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }
}