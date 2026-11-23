package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegisterStep3Binding
import com.iset.fastcolloc.ui.AuthViewModel

class RegisterStep3Fragment : Fragment(R.layout.fragment_register_step3) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterStep3Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegisterStep3Binding.bind(view)

        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        val smokingOptions = listOf("Non-fumeur", "Fumeur", "Fumeur occasionnel")
        val smokingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, smokingOptions).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spSmoking.adapter = smokingAdapter

        binding.btnNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterStep4Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
