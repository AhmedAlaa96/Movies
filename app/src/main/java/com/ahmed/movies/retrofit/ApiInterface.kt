package com.ahmed.movies.retrofit

import com.ahmed.movies.data.models.dto.MovieDetailsResponse
import com.ahmed.movies.data.models.dto.MoviesListResponse
import com.ahmed.movies.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET(Constants.URL.GET_MOVIES)
    suspend fun getMoviesList(@Query(Constants.QueryParams.PAGE) page: Int): MoviesListResponse

    @GET(Constants.URL.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int?): MovieDetailsResponse

}
