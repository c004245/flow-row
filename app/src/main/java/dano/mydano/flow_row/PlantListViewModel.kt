package dano.mydano.flow_row

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dano.mydano.flow_row.data.NoGrowZone
import dano.mydano.flow_row.data.repository.PlantRepository
import kotlinx.coroutines.flow.*

class PlantListViewModel internal constructor(
    private val plantRepository: PlantRepository
): ViewModel() {

    //The current growZone selection.
    private val growZone = MutableLiveData(NoGrowZone)

    //The current growZone selection (flow version)
    private val growZoneFlow = MutableStateFlow(NoGrowZone)

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _snackbar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackbar


    init {
        clearGrowZoneNumber()

        //growZoneFlow에 최근 값만 방출한다.
        growZoneFlow.mapLatest { growZone ->
            _spinner.value = true
            if (growZone == NoGrowZone) {
                plantRepository.tryUpdateRecentPlantsCache()

            } else {

            }
        }.onEach { _spinner.value = false } //모든 데이터마다.
            .catch { throwable -> _snackbar.value = throwable.message }
            .launchIn(viewModelScope)

    }

    fun clearGrowZoneNumber() {
        growZone.value = NoGrowZone
        growZoneFlow.value = NoGrowZone
    }

    fun onSnackbarShown() {
        _snackbar.value = null
    }

}