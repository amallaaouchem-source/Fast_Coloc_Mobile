package com.iset.fastcolloc.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegisterStep5Binding
import com.iset.fastcolloc.ui.AuthViewModel

class RegisterStep5Fragment : Fragment(R.layout.fragment_register_step5) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterStep5Binding? = null
    private val binding get() = _binding!!

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.ivPreview.setImageURI(it)
            binding.ivPreview.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Photo sélectionnée !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegisterStep5Binding.bind(view)

        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.btnAddPhoto.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnNext.setOnClickListener {
            val email = vm.regEmail.ifEmpty { "user@example.com" }
            val password = vm.regPassword.ifEmpty { "password12345" }

            vm.signup(email, password) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, RegistrationSuccessFragment())
                    .commit()
            }
        }

        vm.message.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
