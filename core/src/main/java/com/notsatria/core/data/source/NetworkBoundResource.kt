package com.notsatria.core.data.source

import android.util.Log
import com.notsatria.core.data.source.remote.network.ApiResponse
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                Log.i("NetworkBoundResource", "ApiResponse success: ${apiResponse.data} ")
                saveCallResult(apiResponse.data)
//                emit(Resource.Success(mapCallResult(apiResponse.data)))
                emitAll(loadFromDB().map { Resource.Success(it) })
            }

            is ApiResponse.Empty -> {
                 emitAll(loadFromDB().map { Resource.Success(it) })
            }

            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

//    protected abstract fun mapCallResult(data: RequestType): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}