package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.presentation.adapter.BooksAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesBooksAdapter(): BooksAdapter {
        return BooksAdapter()
    }
}