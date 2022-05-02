package com.rawtooth.mvvmpaging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rawtooth.mvvmpaging.data.Dog
import com.rawtooth.mvvmpaging.data.repository.DogsPaging
import com.rawtooth.mvvmpaging.network.MyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val api:MyApi):ViewModel() {
    fun getAllDos(): Flow<PagingData<Dog>> = Pager(
        config = PagingConfig(30, enablePlaceholders = false)

    ){
        DogsPaging(api)
    }.flow.cachedIn(viewModelScope)
}