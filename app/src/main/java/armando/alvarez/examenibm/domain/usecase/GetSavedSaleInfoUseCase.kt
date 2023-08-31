package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetSavedSaleInfoUseCase(
    private val repository: BooksRepository
) {
    suspend fun execute(): Flow<List<SaleInfo>> {
        return repository.getSavedSaleInfo()
    }
}