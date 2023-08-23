package com.ahmed.movies.domain.usecases.movieslist

import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.ui.base.IBaseUseCase
import kotlinx.coroutines.flow.Flow

interface IMoviesListUseCase: IBaseUseCase {
    fun getMoviesList(pageModel: PageModel): Flow<Status<MoviesListResponse>>
}