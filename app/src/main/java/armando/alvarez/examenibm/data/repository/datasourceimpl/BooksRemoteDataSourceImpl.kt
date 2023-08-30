package armando.alvarez.examenibm.data.repository.datasourceimpl

import armando.alvarez.examenibm.data.api.BooksApiService
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import retrofit2.Response

class BooksRemoteDataSourceImpl(
    private val booksApiService: BooksApiService
) : BooksRemoteDataSource {
    override suspend fun getBooks(title: String, page: Int, results: Int): Response<BooksResponse> {
        return booksApiService.getBooks(title, page, results)
    }
}