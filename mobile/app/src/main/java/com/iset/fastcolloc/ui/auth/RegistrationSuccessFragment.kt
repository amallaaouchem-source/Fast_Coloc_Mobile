package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegistrationSuccessBinding
import com.iset.fastcolloc.ui.LoginFragment

class RegistrationSuccessFragment : Fragment(R.layout.fragment_registration_success) {
    private var _binding: FragmentRegistrationSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegistrationSuccessBinding.bind(view)

        binding.btnGoToApp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
