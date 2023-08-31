package armando.alvarez.examenibm.data.repository

import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo
import armando.alvarez.examenibm.data.repository.datasource.BooksLocalDataSource
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.data.util.Resource
import armando.alvarez.examenibm.data.util.Util
import kotlinx.coroutines.flow.Flow

@Suppress("NAME_SHADOWING")
class BooksRepositoryImpl(
    private val booksRemoteDataSource: BooksRemoteDataSource,
    private val booksLocalDataSource: BooksLocalDataSource
) : BooksRepository {
    override suspend fun getBooks(
        title: String,
        startIndex: Int,
        results: Int
    ): Resource<BooksResponse> {
        return Util.responseToResource(booksRemoteDataSource.getBooks(title, startIndex, results))
    }

    override suspend fun getFilteredBooks(
        title: String,
        filter: String,
        startIndex: Int,
        results: Int
    ): Resource<BooksResponse> {
        return Util.responseToResource(
            booksRemoteDataSource.getFilteredBooks(
                title,
                filter,
                startIndex,
                results
            )
        )
    }


    override suspend fun saveBook(book: Book) {
        book.saleInfo?.bookId = book.id!!
        book.volumeInfo?.bookId = book.id!!
        book.volumeInfo?.image = book.volumeInfo?.getImageLink()
        book.volumeInfo?.author = book.volumeInfo?.getAuthors()
        book.saleInfo?.price = book.saleInfo?.getBookPrice()

        booksLocalDataSource.saveVolumeInfo(book.volumeInfo!!)
        booksLocalDataSource.saveSaleInfo(book.saleInfo!!)

    }

    override suspend fun deleteBook(id: String) {
        booksLocalDataSource.deleteVolumeInfo(id)
        booksLocalDataSource.deleteSaleInfo(id)
    }

    override suspend fun getSavedVolumeInfo(): Flow<List<VolumeInfo>> =
        booksLocalDataSource.getAllVolumeInfo()


    override suspend fun getSavedSaleInfo(): Flow<List<SaleInfo>> = booksLocalDataSource.getAllSaleInfo()

    override suspend fun isSaved(id: String): Flow<Int> = booksLocalDataSource.isSaved(id)
}