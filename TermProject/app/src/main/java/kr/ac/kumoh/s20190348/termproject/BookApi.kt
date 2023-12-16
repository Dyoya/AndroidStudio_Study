package kr.ac.kumoh.s20190348.termproject

import retrofit2.http.GET

interface BookApi {
    @GET("book")
    suspend fun getBooks(): List<Book>
}