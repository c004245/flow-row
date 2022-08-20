package dano.mydano.flow_row.data.repository

import dano.mydano.flow_row.NetworkService
import dano.mydano.flow_row.data.GrowZone
import dano.mydano.flow_row.data.NoGrowZone

class PlantRepository private constructor(
    private val plantService: NetworkService
) {

    /**
     * Update the plants cache.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    suspend fun tryUpdateRecentPlantsCache() {
        if (shouldUpdatePlantsCache(NoGrowZone)) fetchRecentPlants()
    }

    /**
     * Fetch a new list of plants from the network, and append them to [PlantDao]
     */
    private suspend fun fetchRecentPlants() {
        val plants = plantService.allPlants()
//        plants
    }

    /**
     * Returns true if we should make a network request
     */
    private suspend fun shouldUpdatePlantsCache(growZone: GrowZone): Boolean {
        return true
    }
}