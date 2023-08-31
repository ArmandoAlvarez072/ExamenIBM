package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.domain.usecase.GetBooksUseCase
import armando.alvarez.examenibm.domain.usecase.GetFilteredBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetBooksUseCase(
        booksRepository: BooksRepository
    ): GetBooksUseCase {
        return GetBooksUseCase(booksRepository)
    }

    @Singleton
    @Provides
    fun providesGetFilteredBooksUseCase(
        booksRepository: BooksRepository
    ): GetFilteredBooksUseCase {
        return GetFilteredBooksUseCase(booksRepository)
    }
}


















