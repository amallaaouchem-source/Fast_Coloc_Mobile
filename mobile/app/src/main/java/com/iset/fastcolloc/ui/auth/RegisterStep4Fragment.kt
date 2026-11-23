package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegisterStep4Binding
import com.iset.fastcolloc.ui.AuthViewModel

class RegisterStep4Fragment : Fragment(R.layout.fragment_register_step4) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterStep4Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegisterStep4Binding.bind(view)

        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.btnNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterStep5Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
