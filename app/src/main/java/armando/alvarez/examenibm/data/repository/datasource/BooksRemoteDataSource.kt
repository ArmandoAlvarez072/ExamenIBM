package armando.alvarez.examenibm.data.repository.datasource

import armando.alvarez.examenibm.data.model.BooksResponse
import retrofit2.Response

interface BooksRemoteDataSource {
    suspend fun getBooks(title: String, startIndex: Int, results: Int): Response<BooksResponse>
    suspend fun getFilteredBooks(
        title: String,
        filter: String,
        startIndex: Int,
        results: Int
    ): Response<BooksResponse>
}