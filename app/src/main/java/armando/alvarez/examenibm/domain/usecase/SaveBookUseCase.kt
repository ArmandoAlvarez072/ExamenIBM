package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.data.model.Book
import armando.alvarez.examenibm.domain.repository.BooksRepository

class SaveBookUseCase(
    private val repository: BooksRepository
) {
    suspend fun execute(book: Book) {
        return repository.saveBook(book)
    }
}