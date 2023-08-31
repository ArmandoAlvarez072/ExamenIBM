package armando.alvarez.examenibm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVolumeInfo(volumeInfo: VolumeInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSaleInfo(saleInfo: SaleInfo)

    @Query("SELECT * FROM volume_info")
    fun getAllVolumeInfo(): Flow<List<VolumeInfo>>

    @Query("SELECT * FROM sale_info")
    fun getAllSaleInfo(): Flow<List<SaleInfo>>

    @Query("SELECT * FROM volume_info WHERE bookId = :id")
    fun getVolumeInfoById(id: String): Flow<VolumeInfo>

    @Query("SELECT * FROM sale_info WHERE bookId = :id")
    fun getSaleInfoById(id: String): Flow<SaleInfo>

    @Query("DELETE FROM volume_info WHERE bookId = :id")
    suspend fun deleteVolumeInfo(id: String)

    @Query("DELETE FROM sale_info WHERE bookId = :id")
    suspend fun deleteSaleInfo(id: String)

    @Query("SELECT COUNT (*) FROM volume_info WHERE bookId = :id")
    fun isSaved(id: String): Flow<Int>


}