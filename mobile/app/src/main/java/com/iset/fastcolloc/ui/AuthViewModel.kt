package com.iset.fastcolloc.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iset.fastcolloc.data.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class AuthViewModel(app: Application) : AndroidViewModel(app) {
    private val tokens = TokenStore(app)
    private val repository = AuthRepository(ApiClient.create(tokens), tokens)
    val message = MutableLiveData<String>()
    val loading = MutableLiveData(false)
    val loggedIn = MutableLiveData(repository.hasToken())

    var regFirstName: String = ""
    var regLastName: String = ""
    var regAge: String = ""
    var regGender: String = ""
    var regEmail: String = ""
    var regPassword: String = ""

    fun login(email: String, password: String) = run {
        repository.login(email, password)
        loggedIn.value = true
        message.value = "Connexion réussie. Token JWT enregistré."
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit = {}) = run {
        repository.signup(email, password)
        try {
            repository.login(email, password)
            loggedIn.value = true
            message.value = "Compte créé et connecté avec succès !"
        } catch (e: Exception) {
            message.value = "Compte créé. Connecte-toi."
        }
        onSuccess()
    }

    fun logout() { repository.logout(); loggedIn.value = false; message.value = "Déconnecté." }

    private fun run(action: suspend () -> Unit) {
        if (loading.value == true) return
        viewModelScope.launch {
            loading.value = true
            try {
                action()
            } catch (e: HttpException) {
                message.value = parseHttpError(e)
            } catch (e: Exception) {
                message.value = e.message ?: "Erreur de connexion au serveur."
            } finally {
                loading.value = false
            }
        }
    }

    private fun parseHttpError(e: HttpException): String {
        return try {
            val body = e.response()?.errorBody()?.string()
            if (!body.isNullOrEmpty()) {
                val json = JSONObject(body)
                when {
                    json.has("detail") -> json.getString("detail")
                    json.has("message") -> json.getString("message")
                    json.has("error") -> json.getString("error")
                    else -> body
                }
            } else {
                "Erreur serveur (${e.code()})"
            }
        } catch (ex: Exception) {
            "Erreur HTTP ${e.code()}"
        }
    }
}
