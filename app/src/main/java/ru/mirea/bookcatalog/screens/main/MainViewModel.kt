package ru.mirea.bookcatalog.screens.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mirea.bookcatalog.api.NetworkSingleton
import ru.mirea.bookcatalog.api.service.BookService
import ru.mirea.bookcatalog.entity.Book

class MainViewModel: ViewModel(){

    var booksData = MutableLiveData<ArrayList<Book>>()
    var addictionPageBooksData = MutableLiveData<ArrayList<Book>>()
    var error = MutableLiveData<String>()
    var loading = false
    private var page = 0
    private var lastQuery = ""
    var needMode = false

    fun searchBooks(query: String){
        lastQuery = query
        NetworkSingleton.retrofit.create(BookService::class.java)
                .searchBooks(query, 40, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    booksData.postValue(response.items)
                    completeLoading()
                }, {e ->
                    error.postValue(e.localizedMessage)
                })
    }

    fun loadNewPage(){
        NetworkSingleton.retrofit.create(BookService::class.java)
                .searchBooks(lastQuery, 40, page * 40)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    addictionPageBooksData.postValue(response.items)
                    if (booksData.value != null && response.items != null) booksData.value!!.addAll(response!!.items!!)
                    needMode = true
                    completeLoading()
                }, {e ->
                    Log.e("ERROR", e.message)
                })
    }

    private fun completeLoading(){
        page++
        loading = false
    }
}