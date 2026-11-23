package com.iset.fastcolloc.data

import com.iset.fastcolloc.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun create(tokens: TokenStore): AuthApi {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val token = tokens.get()
            val builder = request.newBuilder()
            if (token != null && !request.url.encodedPath.endsWith("/auth/login") && !request.url.encodedPath.endsWith("/auth/signup")) {
                builder.header("Authorization", "Bearer $token")
            }
            chain.proceed(builder.build())
        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}
