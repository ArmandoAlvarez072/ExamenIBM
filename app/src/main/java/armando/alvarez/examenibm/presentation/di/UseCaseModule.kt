package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.domain.repository.BooksRepository
import armando.alvarez.examenibm.domain.usecase.*
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

    @Singleton
    @Provides
    fun providesGetSavedVolumeInfoUseCase(
        booksRepository: BooksRepository
    ): GetSavedVolumeInfoUseCase {
        return GetSavedVolumeInfoUseCase(booksRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavedSaleInfoUseCase(
        booksRepository: BooksRepository
    ): GetSavedSaleInfoUseCase {
        return GetSavedSaleInfoUseCase(booksRepository)
    }

    @Singleton
    @Provides
    fun providesSaveBookUseCase(
        booksRepository: BooksRepository
    ): SaveBookUseCase {
        return SaveBookUseCase(booksRepository)
    }

    @Singleton
    @Provides
    fun providesDeleteBookUseCase(
        booksRepository: BooksRepository
    ): DeleteBookUseCase {
        return DeleteBookUseCase(booksRepository)
    }

    @Singleton
    @Provides
    fun providesIsSavedUseCase(
        booksRepository: BooksRepository
    ): IsSavedUseCase {
        return IsSavedUseCase(booksRepository)
    }
}


















