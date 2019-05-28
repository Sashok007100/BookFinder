package ru.mirea.bookcatalog.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mirea.bookcatalog.entity.search.SearchResponse

interface BookService {
    @GET("volumes")
    fun searchBooks(@Query("q") query: String, @Query("maxResults") results: Int, @Query("startIndex") startIndex: Int): Single<SearchResponse>
}