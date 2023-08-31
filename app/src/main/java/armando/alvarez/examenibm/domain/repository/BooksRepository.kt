package armando.alvarez.examenibm.domain.repository

import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.util.Resource

interface BooksRepository {
    suspend fun getBooks(title: String, startIndex: Int, results: Int): Resource<BooksResponse>
    suspend fun getFilteredBooks(
        title: String,
        filter: String,
        startIndex: Int,
        results: Int
    ): Resource<BooksResponse>
}