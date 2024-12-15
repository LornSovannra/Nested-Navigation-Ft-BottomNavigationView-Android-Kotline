package com.frodzy.nestednavigationftbottomnavigationview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.frodzy.nestednavigationftbottomnavigationview.R
import com.frodzy.nestednavigationftbottomnavigationview.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setUpEventListeners()

        return binding.root
    }

    private fun setUpEventListeners(){
        binding.apply {
            btnSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

            btnPaymentMethod.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_paymentMethodFragment)
            }
        }
    }

}