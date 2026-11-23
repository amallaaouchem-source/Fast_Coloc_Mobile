package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegisterStep1Binding
import com.iset.fastcolloc.ui.AuthViewModel

open class RegisterStep1Fragment : Fragment(R.layout.fragment_register_step1) {
    protected val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterStep1Binding? = null
    protected val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegisterStep1Binding.bind(view)

        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Les mots de passe ne correspondent pas.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 12) {
                Toast.makeText(requireContext(), "Le mot de passe doit contenir au moins 12 caractères.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir l'adresse e-mail.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.regFirstName = binding.etFirstName.text.toString().trim()
            vm.regLastName = binding.etLastName.text.toString().trim()
            vm.regAge = binding.etAge.text.toString().trim()
            vm.regGender = binding.etGender.text.toString().trim()
            vm.regEmail = email
            vm.regPassword = password

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterStep2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
