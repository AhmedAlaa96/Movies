package com.ahmed.movies.data.remote

import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.data.models.dto.MovieDetailsResponse

interface IRemoteDataSource {

    suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse
    suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse
}