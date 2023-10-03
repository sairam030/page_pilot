package com.shamith.BookApp.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.shamith.BookApp.data.BooksResponse
import com.shamith.BookApp.data.ItemsItem
import com.shamith.BookApp.data.local.room.Book
import com.shamith.BookApp.data.local.room.BookDB
import com.shamith.BookApp.data.local.sharedpreferences.BookPreference
import com.shamith.BookApp.data.remote.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BookRepository(context: Context) {
    private val preferences: BookPreference = BookPreference(context)
    private val dao = BookDB.invoke(context).bookDao
    private val apiService = ApiClient.getApiService()

    fun getBookByQuery(responseHandler : (BooksResponse) -> Unit, errorHandler : (Throwable) -> Unit, books: String) {
        apiService.bookRandomCategory(books).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookById(responseHandler : (ItemsItem) -> Unit, errorHandler : (Throwable) -> Unit, id: String) {
        apiService.bookById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByCategory(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, category: String) {
        apiService.bookSearchByCategory("$category+subject:$category")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun searchBookQuery(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, query: String) {
        apiService.bookSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByTitle(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, title: String) {
        apiService.bookBySearchWithSort("$title+intitle:$title")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }


    fun getBookByAuthor(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, author: String) {
        apiService.bookBySearchWithSort("$author+inauthor:$author")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByPublisher(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, author: String) {
        apiService.bookBySearchWithSort("$author+inpublisher:$author")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getPrefString(key: String): String? {
        return preferences.getString(key)
    }

    fun putPrefString(key: String, value: String) {
        preferences.put(key, value)
    }

    fun putPrefInt(key: String, value: Int) {
        preferences.put(key, value)
    }

    suspend fun addBookmark(book: Book) {
        dao.addBookmark(book)
    }

    fun getBookmark(): LiveData<List<Book>> {
        return dao.getAllBookmark()
    }

    suspend fun deleteBookmark(book: Book) {
        dao.deleteBookmark(book)
    }



}