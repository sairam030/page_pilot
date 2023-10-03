package com.shamith.BookApp.presentation.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shamith.BookApp.data.local.BookRepository

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    var repository: BookRepository = BookRepository(application)

    fun getBookmark() = repository.getBookmark()
}