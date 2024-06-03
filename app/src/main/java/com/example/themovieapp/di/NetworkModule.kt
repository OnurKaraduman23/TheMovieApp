package com.example.themovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.themovieapp.common.Constants.BASE_URL
import com.example.themovieapp.data.local.favorite_movies.MovieDao
import com.example.themovieapp.data.local.favorite_movies.MovieDatabase
import com.example.themovieapp.data.local.favorite_movies.datasource.FavoritesDataSource
import com.example.themovieapp.data.repository.MoviesRepositoryImpl
import com.example.themovieapp.data.service.TheMovieApi
import com.example.themovieapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofitOutfitsApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): TheMovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(TheMovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        api: TheMovieApi,
        favoritesDataSource: FavoritesDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(api, favoritesDataSource)
    }

    // Favorite Database Dependencies
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.getFavorites()
    }

    @Provides
    @Singleton
    fun provideFavoritesDataSource(movieDao: MovieDao): FavoritesDataSource {
        return FavoritesDataSource(movieDao)
    }


}