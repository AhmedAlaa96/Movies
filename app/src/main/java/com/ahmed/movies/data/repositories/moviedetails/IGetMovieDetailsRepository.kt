package com.ahmed.movies.data.repositories.moviedetails

import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.ui.base.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IGetMovieDetailsRepository : IBaseRepository {
    fun getMovieDetails(movieId: Int?): Flow<Status<MovieDetailsResponse>>
}