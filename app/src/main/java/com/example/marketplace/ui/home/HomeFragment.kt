package com.example.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private  lateinit var homeViewModel: HomeViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setData()
        mainButton()
        return root
    }

    fun setData(){

        homeViewModel.text.observe(viewLifecycleOwner, {
            binding.tv1.text = it
        })
    }

    fun mainButton(){
        binding.btnTest.setOnClickListener {
            homeViewModel.ubahData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}