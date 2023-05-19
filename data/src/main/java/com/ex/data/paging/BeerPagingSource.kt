package com.ex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ex.data.api.ApiService
import com.ex.data.common.Constants
import com.ex.domain.model.Beer
import java.lang.Integer.max


/**
 * A [PagingSource] that loads articles for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [Article] specifies that the [PagingSource] fetches an [Article] [List].
 */
class BeerPagingSource(private val apiService: ApiService) : PagingSource<Int, Beer>() {

    companion object{
        private const val STARTING_KEY = 1
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {

        println("***********load******************")
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY

        println("startKey : $startKey")

        // We fetch as many articles as hinted to by params.loadSize

        val data = apiService.beers(startKey)

        println("data : $data")


        val hasNext = data.body()?.size == Constants.PAGE_SIZE

        return LoadResult.Page(
            data =  data.body() ?: emptyList(),
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> startKey - 1
            },
            nextKey = if(hasNext) startKey + 1 else null
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}