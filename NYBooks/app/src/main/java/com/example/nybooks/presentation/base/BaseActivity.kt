package com.example.nybooks.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdResource: Int, showBackButton: Boolean = false) {
        toolbar.title = getString(titleIdResource)
        setSupportActionBar(toolbar)

        if (showBackButton) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}