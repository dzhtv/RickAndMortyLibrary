package com.dzhtv.rickandmortylibrary.presentation.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.dzhtv.rickandmortylibrary.R

abstract class BaseActivity : AppCompatActivity() {


    protected fun hideFragment(fragment: BaseFragment) {
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(fragment)
        }
    }

    protected fun showFragment(fragment: BaseFragment, @IdRes fragmentContainer: Int = R.id.container_main) {
        val ft = supportFragmentManager.beginTransaction()
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.add(fragmentContainer, fragment, fragment::class.java.simpleName)
        }
        ft.commit()
    }

    protected fun replaceFragmentFromBackStack(fragment: BaseFragment, @IdRes fragmentContainer: Int = R.id.container_main) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }
}