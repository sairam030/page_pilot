package com.shamith.BookApp.presentation.explore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shamith.BookApp.data.BooksResponse
import com.shamith.BookApp.data.local.BookRepository

class ExploreViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: BookRepository = BookRepository(application)

    var booksResponse = MutableLiveData<BooksResponse>()

    fun searchBookWithoutTerms(query: String?) {
        query?.let {
            repository.getBookByQuery({
                booksResponse.value = it
            }, {}, it)
        }
    }

    fun searchBookInTitle(title: String) {
        repository.getBookByTitle({
            booksResponse.value = it
        }, {}, title)
    }

    fun searchBookInAuthor(author: String) {
        repository.getBookByAuthor({
            booksResponse.value = it
        }, {}, author)
    }

    fun searchBookInCategory(category: String) {
        repository.getBookByCategory({
            booksResponse.value = it
        }, {}, category)
    }

    fun searchBookInPublisher(publisher: String) {
        repository.getBookByPublisher({
            booksResponse.value = it
        }, {}, publisher)
    }



}