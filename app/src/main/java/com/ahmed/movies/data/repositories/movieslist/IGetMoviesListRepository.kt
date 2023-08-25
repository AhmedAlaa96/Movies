package com.ahmed.movies.data.repositories.movieslist

import com.ahmed.movies.data.models.Status
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.data.models.PageModel
import com.ahmed.movies.ui.base.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IGetMoviesListRepository : IBaseRepository {
    fun getMoviesList(pageModel: PageModel): Flow<Status<MoviesListResponse>>
}