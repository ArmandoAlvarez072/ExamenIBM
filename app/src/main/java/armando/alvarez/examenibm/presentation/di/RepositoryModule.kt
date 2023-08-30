package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.data.repository.BooksRepositoryImpl
import armando.alvarez.examenibm.data.repository.datasource.BooksRemoteDataSource
import armando.alvarez.examenibm.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideBooksRepository(
        booksRemoteDataSource: BooksRemoteDataSource
    ): BooksRepository {
        return BooksRepositoryImpl(
            booksRemoteDataSource
        )
    }

}














