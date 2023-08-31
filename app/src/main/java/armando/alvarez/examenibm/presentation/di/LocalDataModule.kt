package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.data.db.BooksDao
import armando.alvarez.examenibm.data.repository.datasource.BooksLocalDataSource
import armando.alvarez.examenibm.data.repository.datasourceimpl.BooksLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideBooksLocalDataSource(
        booksDao: BooksDao
    ): BooksLocalDataSource {
        return BooksLocalDataSourceImpl(booksDao)
    }

}