package com.example.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nybooks.R
import com.example.nybooks.data.ApiService
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.booksResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }

                        booksLiveData.value = books
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOK_LIST, null)
                    }
                    response.code() == ERROR_401 -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    }
                    else -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400)
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500)
            }
        })
    }

    companion object {
        private const val ERROR_401 = 401
        private const val VIEW_FLIPPER_BOOK_LIST = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}