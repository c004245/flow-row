package dano.mydano.flow_row.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dano.mydano.flow_row.PlantListViewModel
import dano.mydano.flow_row.databinding.FragmentPlantListBinding
import dano.mydano.flow_row.util.Injector
import androidx.fragment.app.viewModels

class PlantListFragment: Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        Injector.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }
}

class PlantListViewModelFactory(

): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = PlantListViewModel() as T
    }

