package dano.mydano.flow_row.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dano.mydano.flow_row.PlantListViewModel
import dano.mydano.flow_row.databinding.FragmentPlantListBinding
import dano.mydano.flow_row.util.Injector
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dano.mydano.flow_row.R
import dano.mydano.flow_row.data.repository.PlantRepository

class PlantListFragment: Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        Injector.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.spinner.observe(viewLifecycleOwner) { show ->
            binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
        }

        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateData() {
        with (viewModel) {

        }
    }

}

class PlantListViewModelFactory(
    private val repository: PlantRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = PlantListViewModel(repository) as T
    }

