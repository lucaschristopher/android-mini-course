package com.example.nybooks.presentation.books

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.presentation.base.BaseActivity
import com.example.nybooks.presentation.details.BookDetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.toolbar.*

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.books_title)

        // ViewModel creation
        val viewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        // Activity listening through a LiveData
        viewModel.booksLiveData.observe(this, Observer {
            // if it not null...
            it?.let { books ->
                with(recycleBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    // Lamba function
                    adapter = BooksAdapter(books) { book ->
                        val intent: Intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResourceId ->
                    textViewError.text = getString(errorMessageResourceId)
                }
            }
        })

        viewModel.getBooks()
    }
}