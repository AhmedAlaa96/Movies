package com.ahmed.movies.data.repositories.movieslist

import com.ahmed.movies.data.local.ILocalDataSource
import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.ui.base.BaseRepository
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesListRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource
): BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource),
    IGetMoviesListRepository {
    override fun getMoviesList(pageModel: PageModel): Flow<Status<MoviesListResponse>> {
        return safeApiCalls {
            mIRemoteDataSource.getMoviesList(pageModel)
        }
    }
}