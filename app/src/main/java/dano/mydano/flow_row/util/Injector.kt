package dano.mydano.flow_row.util

import android.content.Context
import dano.mydano.flow_row.ui.PlantListViewModelFactory

interface ViewModelFactoryProvider {
    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider: ViewModelFactoryProvider {
    override fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        return PlantListViewModelFactory()
    }


}


@Volatile private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider