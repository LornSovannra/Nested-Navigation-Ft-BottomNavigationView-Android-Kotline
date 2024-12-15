package com.frodzy.nestednavigationftbottomnavigationview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.frodzy.nestednavigationftbottomnavigationview.R
import com.frodzy.nestednavigationftbottomnavigationview.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        setUpEventListeners()

        return binding.root
    }

    private fun setUpEventListeners(){
        binding.apply {
            btnPaymentMethod.setOnClickListener {
                findNavController().navigate(R.id.action_searchFragment_to_paymentMethodFragment)
            }
        }
    }

}