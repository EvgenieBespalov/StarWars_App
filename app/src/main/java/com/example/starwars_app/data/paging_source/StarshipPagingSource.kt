package com.example.starwars_app.data.paging_source

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwars_app.data.api.StarshipApi
import com.example.starwars_app.data.converter.ConverterStarship
import com.example.starwars_app.domain.entity.StarshipEntity
import retrofit2.HttpException

class StarshipPagingSource (
    private val starshipApi: StarshipApi,
    private val converter: ConverterStarship,
    private val name: String
): PagingSource<Int, StarshipEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarshipEntity> {
        if (name.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: 1
            val response = starshipApi.searchPlanet(name, pageNumber.toString())

            if (response.isSuccessful) {
                val starships = response.body()!!.results.map { converter.convertModelInEntity(it) }
                val nextPageNumber = if (starships.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

                return LoadResult.Page(starships, prevPageNumber, nextPageNumber)
            } else {
                Log.i(ControlsProviderService.TAG, "PagingSource Error: " + HttpException(response))
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            Log.i(ControlsProviderService.TAG, "PagingSource HttpException: " + e)
            return LoadResult.Error(e)
        } catch (e: Exception) {
            Log.i(ControlsProviderService.TAG, "PagingSource Exception:" + e)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StarshipEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}