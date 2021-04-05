package com.example.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.nybooks.R
import com.example.nybooks.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.toolbar.*

class BookDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        setupToolbar(toolbarMain, R.string.book_details_title, true)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        bookDetailTitle.text = title
        bookDetailDescription.text = description
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}