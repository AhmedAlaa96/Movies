package com.ahmed.movies.data.repositories.moviedetails

import com.ahmed.movies.data.local.ILocalDataSource
import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.di.IODispatcher
import com.ahmed.movies.ui.base.BaseRepository
import com.ahmed.movies.ui.base.IBaseRepository
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    @IODispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource, dispatcher),
    IGetMovieDetailsRepository {
    override fun getMovieDetails(movieId: Int?): Flow<Status<MovieDetailsResponse>> {
        return safeApiCalls {
            mIRemoteDataSource.getMovieDetails(movieId)
        }
    }
}