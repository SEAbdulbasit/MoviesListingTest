package com.example.swvlmobilechallenge.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.databinding.MainFragmentBinding


class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: MainFragmentBinding
    lateinit var adapter: MoviesAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(App.getInstance().getUserRepository())).get(
            MainViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_options, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.action_search)

        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_move_to_top -> {

                // scroll to top position when the move to top option is clicked
                binding.rvMoviesList.smoothScrollToPosition(0)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setAdapter() {
        adapter = MoviesAdapter {
            navigateToPackageDetailsPage(it)
        }

        binding.rvMoviesList.adapter = adapter
    }

    private fun navigateToPackageDetailsPage(movie: MovieResponseModel.Movie) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(
                movie
            )
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesList()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.filterData(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.filterData(newText)
        return false
    }

}