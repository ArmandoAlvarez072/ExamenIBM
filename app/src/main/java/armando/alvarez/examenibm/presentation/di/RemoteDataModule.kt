package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.data.api.BooksApiService
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import armando.alvarez.examenibm.data.repository.datasourceimpl.BooksRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideBooksRemoteDataSource(
        booksApiService: BooksApiService
    ): BooksRemoteDataSource {
        return BooksRemoteDataSourceImpl(booksApiService)
    }

}