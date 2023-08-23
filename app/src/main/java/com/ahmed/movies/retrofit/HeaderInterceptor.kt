package com.ahmed.movies.retrofit

import com.ahmed.movies.utils.Constants.Headers.ACCEPT
import com.ahmed.movies.utils.Constants.Headers.ACCEPT_VALUE
import com.ahmed.movies.utils.Constants.Headers.AUTHORIZATION
import com.ahmed.movies.utils.Constants.Headers.AUTHORIZATION_VALUE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(AUTHORIZATION, AUTHORIZATION_VALUE)
            .addHeader(ACCEPT, ACCEPT_VALUE)
            .build()
        return chain.proceed(request)
    }
}