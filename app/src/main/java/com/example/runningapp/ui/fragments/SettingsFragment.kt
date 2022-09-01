package com.example.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.runningapp.R
import com.example.runningapp.databinding.FragmentSettingsBinding
import com.example.runningapp.ui.MainActivity
import com.example.runningapp.utilities.Constants.KEY_NAME
import com.example.runningapp.utilities.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings){
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()

        binding.btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success){
                Snackbar.make(
                    requireView(),
                    "Saved changes",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                Snackbar.make(
                    requireView(),
                    "Please enter all the fields",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadFieldsFromSharedPref(){
        val name = sharedPreferences.getString(KEY_NAME, "") ?: ""
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean{
        val nameText = binding.etName.text.toString()
        val weightText = binding.etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty()){
            return false
        }

        sharedPreferences.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()
        val toolbarTitle = "Let's go $nameText!"
        (requireActivity() as MainActivity).setTextToolbarTitle(toolbarTitle)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}