package com.notsatria.core.di

import android.content.Context
import androidx.room.Room
import com.notsatria.core.data.source.local.room.MovieDao
import com.notsatria.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passprhase: ByteArray = SQLiteDatabase.getBytes("muuvis123".toCharArray())
        val factory = SupportFactory(passprhase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movie.db"
        )
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}