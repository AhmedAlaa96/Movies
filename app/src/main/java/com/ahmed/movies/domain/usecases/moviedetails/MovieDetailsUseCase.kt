package com.ahmed.movies.domain.usecases.moviedetails

import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.data.repositories.moviedetails.IGetMovieDetailsRepository
import com.ahmed.movies.ui.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val mMoviesDetailsRepository: IGetMovieDetailsRepository) :
    BaseUseCase(mMoviesDetailsRepository),
    IMovieDetailsUseCase {


    override fun getMovieDetails(movieId: Int?): Flow<Status<MovieDetailsResponse>> {
        return mMoviesDetailsRepository.getMovieDetails(movieId)
    }
}