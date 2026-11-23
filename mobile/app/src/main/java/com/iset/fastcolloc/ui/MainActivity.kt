package com.iset.fastcolloc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.activity.viewModels
import com.iset.fastcolloc.R

class MainActivity : AppCompatActivity() {
    val vm: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) supportFragmentManager.commit {
            replace(R.id.fragmentContainer, if (vm.loggedIn.value == true) HomeFragment() else LoginFragment())
        }
        vm.loggedIn.observe(this) { logged ->
            val current = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (logged && current !is HomeFragment) supportFragmentManager.commit { replace(R.id.fragmentContainer, HomeFragment()) }
            if (!logged && current !is LoginFragment) supportFragmentManager.commit { replace(R.id.fragmentContainer, LoginFragment()) }
        }
    }
}
