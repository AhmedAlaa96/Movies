package com.ahmed.movies.domain.usecases.moviedetails

import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.ui.base.IBaseUseCase
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsUseCase: IBaseUseCase {
    fun getMovieDetails(movieId: Int?): Flow<Status<MovieDetailsResponse>>
}