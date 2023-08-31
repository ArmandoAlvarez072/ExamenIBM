package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.data.util.Resource

class GetBooksUseCase(
    private val repository: BooksRepository
) {

    suspend fun execute(title: String, startIndex: Int, results: Int): Resource<BooksResponse> {
        return repository.getBooks(title, startIndex, results)
    }
}