package com.example.starwars_app.domain.paging_source

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwars_app.data.api.PeopleApi
import com.example.starwars_app.data.converter.ConverterPeople
import com.example.starwars_app.domain.entity.PeopleEntity
import retrofit2.HttpException

class PeoplePagingSource(
    private val peopleApi: PeopleApi,
    private val converter: ConverterPeople,
    private val name: String
): PagingSource<Int, PeopleEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PeopleEntity> {
        if (name.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: 1
            val response = peopleApi.searchPeople(name, pageNumber.toString())

            if (response.isSuccessful) {
                val planets = response.body()!!.results.map { converter.convertModelInEntity(it) }
                val nextPageNumber = if (planets.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

                return LoadResult.Page(planets, prevPageNumber, nextPageNumber)
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

    override fun getRefreshKey(state: PagingState<Int, PeopleEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}