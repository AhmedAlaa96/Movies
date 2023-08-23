package com.ahmed.movies.data.remote

import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.retrofit.ApiInterface

class RemoteDataSource(private val mRetrofitInterface: ApiInterface) : IRemoteDataSource {
    override suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse {
        return mRetrofitInterface.getMoviesList(pageModel.page)
    }

    override suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse {
        return mRetrofitInterface.getMovieDetails(movieId)
    }

}