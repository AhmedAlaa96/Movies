package com.ahmed.movies.ui.moviedetails.di

import com.ahmed.movies.data.local.ILocalDataSource
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.repositories.moviedetails.GetMovieDetailsRepository
import com.ahmed.movies.data.repositories.moviedetails.IGetMovieDetailsRepository
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.domain.usecases.moviedetails.IMovieDetailsUseCase
import com.ahmed.movies.domain.usecases.moviedetails.MovieDetailsUseCase
import com.ahmed.movies.retrofit.RetrofitModule
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
abstract class MovieDetailsModule {

    companion object{
        @Singleton
        @Provides
        fun provideMoviesDetailsRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mILocalDataSource: ILocalDataSource,
            mIPreferencesDataSource: IPreferencesDataSource
        ): IGetMovieDetailsRepository {
            return GetMovieDetailsRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource
            )
        }
    }

    @Singleton
    @Binds
    abstract fun bindIMoviesDetailsUseCase(movieDetailsUseCase: MovieDetailsUseCase): IMovieDetailsUseCase


}