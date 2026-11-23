package com.iset.fastcolloc.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentHomeSimpleBinding

class HomeFragment : Fragment(R.layout.fragment_home_simple) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentHomeSimpleBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, state: Bundle?) {
        _binding = FragmentHomeSimpleBinding.bind(view)
        binding.btnLogout.setOnClickListener { vm.logout() }
        vm.message.observe(viewLifecycleOwner) { binding.tvHomeMessage.text = it }
    }
    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
