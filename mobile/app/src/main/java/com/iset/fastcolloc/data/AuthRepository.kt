package com.iset.fastcolloc.data

class AuthRepository(private val api: AuthApi, private val tokens: TokenStore) {
    suspend fun login(email: String, password: String) {
        val result = api.login(LoginRequest(email.trim(), password))
        require(result.access_token.isNotBlank()) { "Le serveur n'a pas retourné de token." }
        tokens.save(result.access_token)
    }

    suspend fun signup(email: String, password: String) {
        require(email.isNotBlank() && password.length >= 12) { "Vérifie l'e-mail et utilise au moins 12 caractères." }
        api.signup(SignupRequest(email.trim(), password))
    }

    fun logout() = tokens.clear()
    fun hasToken() = tokens.get() != null
}
