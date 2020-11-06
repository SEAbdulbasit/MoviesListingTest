package com.example.swvlmobilechallenge.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.swvlmobilechallenge.databinding.MovieDetailsFragmentBinding
import com.example.swvlmobilechallenge.ui.main.asDomainModel

class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this, MovieDetailsViewModel.Factory(args.movie.asDomainModel())).get(
            MovieDetailsViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
    }

    private fun setAdapters() {
        val adapter = ImagesAdapter()
        binding.rvImages.adapter = adapter
    }
}