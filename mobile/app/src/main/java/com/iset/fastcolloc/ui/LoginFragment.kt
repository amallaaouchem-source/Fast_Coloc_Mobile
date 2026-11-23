package com.iset.fastcolloc.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, state: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        binding.btnLogin.setOnClickListener { vm.login(binding.etLoginEmail.text.toString(), binding.etLoginPassword.text.toString()) }
        binding.btnOpenRegister.setOnClickListener { (activity as androidx.appcompat.app.AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, RegisterFragment()).addToBackStack(null).commit() }
        binding.btnGoogle.visibility = View.GONE
        binding.btnForgot.visibility = View.GONE
        vm.message.observe(viewLifecycleOwner) { binding.tvLoginError.text = it; binding.tvLoginError.visibility = View.VISIBLE }
    }
    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
