package com.ahmed.movies.ui.movieslist.di

import com.ahmed.movies.data.local.ILocalDataSource
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.repositories.movieslist.GetMoviesListRepository
import com.ahmed.movies.data.repositories.movieslist.IGetMoviesListRepository
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.domain.usecases.movieslist.IMoviesListUseCase
import com.ahmed.movies.domain.usecases.movieslist.MoviesListUseCase
import com.ahmed.movies.retrofit.RetrofitModule
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
abstract class MoviesListModule {

    companion object{
        @Singleton
        @Provides
        fun provideMoviesListingRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mILocalDataSource: ILocalDataSource,
            mIPreferencesDataSource: IPreferencesDataSource
        ): IGetMoviesListRepository {
            return GetMoviesListRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource
            )
        }
    }

    @Singleton
    @Binds
    abstract fun bindIMoviesListUseCase(moviesUseCase: MoviesListUseCase): IMoviesListUseCase


}