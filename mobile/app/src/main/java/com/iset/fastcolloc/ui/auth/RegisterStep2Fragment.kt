package com.iset.fastcolloc.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iset.fastcolloc.R
import com.iset.fastcolloc.databinding.FragmentRegisterStep2Binding
import com.iset.fastcolloc.ui.AuthViewModel

class RegisterStep2Fragment : Fragment(R.layout.fragment_register_step2) {
    private val vm: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterStep2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        _binding = FragmentRegisterStep2Binding.bind(view)

        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        val countries = listOf("Tunisie")
        val countryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spCountry.adapter = countryAdapter

        val governorates = listOf(
            "Tunis", "Ariana", "Ben Arous", "La Manouba", "Nabeul", "Zaghouan", "Bizerte",
            "Béja", "Jendouba", "Le Kef", "Siliana", "Kairouan", "Kasserine", "Sidi Bouzid",
            "Sousse", "Monastir", "Mahdia", "Sfax", "Gafsa", "Tozeur", "Kébili", "Gabès",
            "Médenine", "Tataouine"
        )
        val govAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, governorates).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spGovernorate.adapter = govAdapter

        val localities = listOf(
            "Tunis Ville", "Ariana Ville", "La Marsa", "Carthage", "Sidi Bou Said", "Le Kram",
            "La Goulette", "Menzah", "Manar", "El Ghazela", "Ennasr", "Lac 1", "Lac 2",
            "Sousse Ville", "Sfax Ville", "Bizerte Ville", "Monastir Ville", "Nabeul Ville", "Autre"
        )
        val locAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, localities).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spLocality.adapter = locAdapter

        binding.btnNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterStep3Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
