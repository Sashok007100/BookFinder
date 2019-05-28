package ru.mirea.bookcatalog.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkSingleton{
    val retrofit: Retrofit
        get() {
            val client = OkHttpClient.Builder()
                    .build()
            return Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .build()
        }
}