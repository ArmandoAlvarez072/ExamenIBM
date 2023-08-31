package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.data.model.BooksResponse
import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.data.util.Resource

class GetFilteredBooksUseCase(
    private val repository: BooksRepository
) {

    suspend fun execute(
        title: String,
        filter: String,
        page: Int,
        results: Int
    ): Resource<BooksResponse> {
        return repository.getFilteredBooks(title, filter, page, results)
    }
}