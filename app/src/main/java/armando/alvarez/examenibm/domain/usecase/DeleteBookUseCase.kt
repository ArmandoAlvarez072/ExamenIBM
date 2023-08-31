package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.domain.repository.BooksRepository

class DeleteBookUseCase(
    private val repository: BooksRepository
) {
    suspend fun execute(id: String) {
        return repository.deleteBook(id)
    }
}