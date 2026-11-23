package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
