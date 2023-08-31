package armando.alvarez.examenibm.data.repository.datasource

import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    fun getAllVolumeInfo(): Flow<List<VolumeInfo>>
    fun getAllSaleInfo(): Flow<List<SaleInfo>>

    fun getVolumeInfoById(id:String): Flow<VolumeInfo>
    fun getSaleInfoById(id:String): Flow<SaleInfo>

    fun isSaved(id:String): Flow<Int>

    suspend fun saveVolumeInfo(volumeInfo: VolumeInfo)
    suspend fun saveSaleInfo(saleInfo: SaleInfo)

    suspend fun deleteVolumeInfo(id: String)
    suspend fun deleteSaleInfo(id: String)

}