package com.example.countryquizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countryquizapp.R
import com.example.countryquizapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding:FragmentResultBinding?=null
    private val binding get() = _binding!!
    private val getArgs:ResultFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correct=getArgs.correct
        val wrong=getArgs.wrong
        binding.correctText.text="$correct"
        binding.wrongText.text="$wrong "
        binding.replayButton.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

    }

}