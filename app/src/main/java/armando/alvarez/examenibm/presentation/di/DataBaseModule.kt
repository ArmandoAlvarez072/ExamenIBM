package armando.alvarez.examenibm.presentation.di

import android.content.Context
import androidx.room.Room
import armando.alvarez.examenibm.data.db.BooksDao
import armando.alvarez.examenibm.data.db.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): BooksDatabase {
        return Room.databaseBuilder(
            appContext,
            BooksDatabase::class.java,
            "books_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBooksDao(booksDatabase: BooksDatabase): BooksDao {
        return booksDatabase.booksDao()
    }



}