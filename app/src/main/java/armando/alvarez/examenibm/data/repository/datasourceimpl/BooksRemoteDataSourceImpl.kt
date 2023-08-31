package armando.alvarez.examenibm.data.repository.datasourceimpl

import armando.alvarez.examenibm.data.api.BooksApiService
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import retrofit2.Response

class BooksRemoteDataSourceImpl(
    private val booksApiService: BooksApiService
) : BooksRemoteDataSource {
    override suspend fun getBooks(title: String, startIndex: Int, results: Int): Response<BooksResponse> {
        return booksApiService.getBooks(title, startIndex, results)
    }

    override suspend fun getFilteredBooks(
        title: String,
        filter: String,
        startIndex: Int,
        results: Int
    ): Response<BooksResponse> {
        return booksApiService.getFilteredBooks(title, filter, startIndex, results)
    }
}