package com.iset.fastcolloc.data

import retrofit2.http.Body
import retrofit2.http.POST

// The backend returns these token fields after a successful login.
data class TokenResponse(
    val access_token: String,
    val refresh_token: String? = null,
    val expires_in: Long? = null
)
data class LoginRequest(val email: String, val password: String)
data class SignupRequest(val email: String, val password: String)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): TokenResponse

    @POST("auth/signup?client=android")
    suspend fun signup(@Body request: SignupRequest)
}
