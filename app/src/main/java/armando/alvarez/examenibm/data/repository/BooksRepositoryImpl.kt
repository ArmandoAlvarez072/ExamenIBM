package armando.alvarez.examenibm.data.repository

import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.data.util.Util

class BooksRepositoryImpl(
    private val booksRemoteDataSource: BooksRemoteDataSource
) : BooksRepository {
    override suspend fun getBooks(title: String, page: Int, results: Int): Resource<BooksResponse> {
        return Util.responseToResource(booksRemoteDataSource.getBooks(title, page, results))
    }

    override suspend fun getFilteredBooks(
        title: String,
        filter: String,
        page: Int,
        results: Int
    ): Resource<BooksResponse> {
        return Util.responseToResource(booksRemoteDataSource.getFilteredBooks(title,filter, page, results))
    }
}