package com.shamith.BookApp.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shamith.BookApp.data.local.BookRepository
import com.shamith.BookApp.data.local.room.Book
import com.shamith.BookApp.helper.constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    var repository: BookRepository = BookRepository(application)

    fun saveRecentBookId(id: String){
        repository.putPrefString(constant.PREF_RECENT_BOOK_ID, id)
    }

    fun addBookmark(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmark(book)
        }
    }

    fun deleteBookmark(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmark(book)
        }
    }
}