package armando.alvarez.examenibm.data.api

import armando.alvarez.examenibm.data.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q")
        title: String,
        @Query("startIndex")
        page: Int,
        @Query("maxResults")
        results: Int
    ) : Response<BooksResponse>
}