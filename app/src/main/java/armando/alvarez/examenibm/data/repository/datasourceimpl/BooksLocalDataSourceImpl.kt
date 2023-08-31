package armando.alvarez.examenibm.data.repository.datasourceimpl

import armando.alvarez.examenibm.data.db.BooksDao
import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo
import armando.alvarez.examenibm.data.repository.datasource.BooksLocalDataSource
import kotlinx.coroutines.flow.Flow

class BooksLocalDataSourceImpl(
    private val booksDao: BooksDao
) : BooksLocalDataSource {
    override fun getAllVolumeInfo(): Flow<List<VolumeInfo>> = booksDao.getAllVolumeInfo()

    override fun getAllSaleInfo(): Flow<List<SaleInfo>> = booksDao.getAllSaleInfo()

    override fun getVolumeInfoById(id: String): Flow<VolumeInfo> = booksDao.getVolumeInfoById(id)

    override fun getSaleInfoById(id: String): Flow<SaleInfo> = booksDao.getSaleInfoById(id)

    override fun isSaved(id: String): Flow<Int> = booksDao.isSaved(id)

    override suspend fun saveVolumeInfo(volumeInfo: VolumeInfo) =
        booksDao.saveVolumeInfo(volumeInfo)

    override suspend fun saveSaleInfo(saleInfo: SaleInfo) = booksDao.saveSaleInfo(saleInfo)

    override suspend fun deleteVolumeInfo(id: String) = booksDao.deleteVolumeInfo(id)

    override suspend fun deleteSaleInfo(id: String) = booksDao.deleteSaleInfo(id)
}