package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class IsSavedUseCase(
    private val repository: BooksRepository
) {
    suspend fun execute(id: String): Flow<Int> {
        return repository.isSaved(id)
    }
}