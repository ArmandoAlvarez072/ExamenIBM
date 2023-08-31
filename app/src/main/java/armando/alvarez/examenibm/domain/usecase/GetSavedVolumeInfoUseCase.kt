package armando.alvarez.examenibm.domain.usecase

import armando.alvarez.examenibm.data.model.VolumeInfo
import armando.alvarez.examenibm.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetSavedVolumeInfoUseCase(
    private val repository: BooksRepository
) {
    suspend fun execute(): Flow<List<VolumeInfo>> {
        return repository.getSavedVolumeInfo()
    }
}