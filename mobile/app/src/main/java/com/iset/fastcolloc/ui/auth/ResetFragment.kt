package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentResetBinding

class ResetFragment : Fragment(R.layout.fragment_reset) {
    private var _binding: FragmentResetBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentResetBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
