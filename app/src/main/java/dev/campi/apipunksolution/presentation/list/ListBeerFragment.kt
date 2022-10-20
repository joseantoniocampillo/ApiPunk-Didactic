package dev.campi.apipunksolution.presentation.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.campi.apipunksolution.R
import dev.campi.apipunksolution.databinding.FragmentListBinding
import dev.campi.apipunksolution.presentation.SharedViewModel
import dev.campi.apipunksolution.presentation.utils.launchRepeatOnStarted
import dev.campi.apipunksolution.presentation.utils.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class ListBeerFragment: Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()

    @Inject
    lateinit var beerAdapter: BeerAdapter

    override fun onResume() {
        super.onResume()
        binding.beerRecyclerView.adapter = beerAdapter
        launchRepeatOnStarted {
            viewModel.beerList.collect {
                beerAdapter.submitList(it)
            }
        }
    }
}