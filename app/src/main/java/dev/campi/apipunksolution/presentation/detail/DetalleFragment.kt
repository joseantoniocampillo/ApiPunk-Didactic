package dev.campi.apipunksolution.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.campi.apipunksolution.R
import dev.campi.apipunksolution.databinding.FragmentDetalleBinding
import dev.campi.apipunksolution.presentation.SharedViewModel
import dev.campi.apipunksolution.presentation.utils.launchRepeatOnStarted
import dev.campi.apipunksolution.presentation.utils.viewBinding
import kotlinx.coroutines.delay

class DetalleFragment : Fragment(R.layout.fragment_detalle) {

    private val binding by viewBinding(FragmentDetalleBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRepeatOnStarted {
            delay(100L)
        }
        binding
    }

}