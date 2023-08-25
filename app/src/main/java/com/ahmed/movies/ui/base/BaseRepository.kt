package com.ahmed.movies.ui.base

import android.util.Log
import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.remote.IRemoteDataSource
import com.ahmed.movies.data.shared_prefrences.IPreferencesDataSource
import com.ahmed.movies.utils.Utils
import com.ahmed.movies.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketException

abstract class BaseRepository(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IBaseRepository {


    protected val isConnected: Boolean
        get() {
            return connectionUtils.isConnected
        }


    fun <T> safeApiCalls(
        apiCall: suspend () -> T
    ): Flow<Status<T>> {
        return flow {
            if (isConnected) {
                try {
                    emit(Status.Success(apiCall.invoke()))
                } catch (throwable: Throwable) {
                    Utils.printStackTrace(throwable)
                    when (throwable) {
                        is HttpException -> {
                            emit(Status.Error(error = throwable.message)) as Unit
                        }
                        is SocketException -> {
                            emit(Status.Error(error = throwable.message)) as Unit
                        }
                        else -> {
                            emit(Status.Error(error = throwable.message)) as Unit
                        }
                    }
                }
            } else {
                emit(Status.NoNetwork(error = "No Network")) as Unit
            }
        }.flowOn((dispatcher)) as Flow<Status<T>>
    }
}
