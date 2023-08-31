package armando.alvarez.examenibm.domain.repository

import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo
import armando.alvarez.examenibm.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooks(title: String, startIndex: Int, results: Int): Resource<BooksResponse>
    suspend fun getFilteredBooks(
        title: String,
        filter: String,
        startIndex: Int,
        results: Int
    ): Resource<BooksResponse>

    suspend fun saveBook(book: Book)

    suspend fun deleteBook(id: String)

    suspend fun getSavedVolumeInfo(): Flow<List<VolumeInfo>>
    suspend fun getSavedSaleInfo(): Flow<List<SaleInfo>>
    suspend fun isSaved(id: String): Flow<Int>

}