package com.ahmed.movies.data.repositories.movieslist

import com.ahmed.movies.data.local.ILocalDataSource
import com.ahmed.movies.data.local.LocalDataSource
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.data.models.StatusCode
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.remote.RemoteDataSource
import com.ahmed.movies.data.repositories.movieslist.GetMoviesListRepository
import com.ahmed.movies.data.repositories.movieslist.IGetMoviesListRepository
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.data.shared_prefrences.PreferencesDataSource
import com.ahmed.movies.utils.BaseUnitTest
import com.ahmed.movies.utils.connection_utils.ConnectionUtils
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException


@Suppress("DEPRECATION")
@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesListRepositoryShould : BaseUnitTest() {

    private val remoteDataSource: IRemoteDataSource = mock<RemoteDataSource>()
    private val connectionUtils: IConnectionUtils = mock<ConnectionUtils>()
    private val localDataSource: ILocalDataSource = mock<LocalDataSource>()
    private val sharedPreferences: IPreferencesDataSource = mock<PreferencesDataSource>()
    private val pageModel = PageModel()
    private val moviesListResponse: MoviesListResponse = mock()
    private val noNetworkError: String = "No Network"
    private val exception = RuntimeException("Something Went Wrong")

    @Test
    fun emitMoviesListResponseFromRemoteDataSource() = runBlockingTest {
        val repository = initSuccessRepository()
        val response = repository.getMoviesList(pageModel).first()
        assertEquals(StatusCode.SUCCESS, response.statusCode)
        assertEquals(moviesListResponse, response.data)
    }

    @Test
    fun propagateNoNetworkError() = runBlockingTest {
        val repository = initNoNetworkRepository()
        val response = repository.getMoviesList(pageModel).first()
        assertEquals(StatusCode.NO_NETWORK, response.statusCode)
        assertEquals(noNetworkError, response.error)
    }
    @Test
    fun propagateGetMoviesListResponseError() = runBlockingTest {
        val repository = initFailureRepository()
        val response = repository.getMoviesList(pageModel).first()
        assertEquals(StatusCode.ERROR, response.statusCode)
        assertEquals(exception.message, response.error)
    }


    private suspend fun initSuccessRepository(): IGetMoviesListRepository {

        whenever(connectionUtils.isConnected).thenReturn(true)

        whenever(remoteDataSource.getMoviesList(pageModel)).thenReturn(
            moviesListResponse
        )
        return GetMoviesListRepository(
            connectionUtils,
            remoteDataSource,
            localDataSource,
            sharedPreferences,
            Dispatchers.Unconfined
        )
    }

    private suspend fun initFailureRepository(): IGetMoviesListRepository {

        whenever(connectionUtils.isConnected).thenReturn(true)

        whenever(remoteDataSource.getMoviesList(pageModel)).thenThrow(
            exception
        )
        return GetMoviesListRepository(
            connectionUtils,
            remoteDataSource,
            localDataSource,
            sharedPreferences,
            Dispatchers.Unconfined
        )
    }

    private fun initNoNetworkRepository(): IGetMoviesListRepository {

        whenever(connectionUtils.isConnected).thenReturn(false)

        return GetMoviesListRepository(
            connectionUtils,
            remoteDataSource,
            localDataSource,
            sharedPreferences,
            Dispatchers.Unconfined
        )
    }
}