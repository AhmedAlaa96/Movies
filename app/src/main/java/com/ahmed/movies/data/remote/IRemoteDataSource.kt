package com.ahmed.movies.data.remote

import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel

interface IRemoteDataSource {

    suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse
}