package com.iset.fastcolloc.data

import android.content.Context

class TokenStore(context: Context) {
    private val prefs = context.getSharedPreferences("fastcoloc_tokens", Context.MODE_PRIVATE)

    fun save(token: String) { prefs.edit().putString("access_token", token).apply() }
    fun get(): String? = prefs.getString("access_token", null)
    fun clear() { prefs.edit().clear().apply() }
}
