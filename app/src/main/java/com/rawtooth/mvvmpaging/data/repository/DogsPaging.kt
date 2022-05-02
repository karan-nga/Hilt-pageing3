package com.rawtooth.mvvmpaging.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rawtooth.mvvmpaging.data.Dog
import com.rawtooth.mvvmpaging.network.MyApi
import retrofit2.HttpException
import java.io.IOException

class DogsPaging constructor(val api:MyApi) :PagingSource<Int, Dog>(){
    override fun getRefreshKey(state: PagingState<Int, Dog>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dog> {
        val page = params.key ?: 1
        return try {
            val response = api.getAllDogs(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if(page==1)null else page-1,
                nextKey = if(response.isEmpty())null else page+1
            )
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }
}