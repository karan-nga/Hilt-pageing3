package com.rawtooth.mvvmpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rawtooth.mvvmpaging.adapter.DogAdapter
import com.rawtooth.mvvmpaging.adapter.LoadingStateAdapter
import com.rawtooth.mvvmpaging.databinding.ActivityMainBinding
import com.rawtooth.mvvmpaging.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    @Inject
    lateinit var dogAdapter: DogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDos().collectLatest {response ->
                binding.apply {
                    recyclerview.isVisible=true
                    progressBar.isVisible=false
                }
                dogAdapter.submitData(response)
            }
        }

    }

    private fun initRecycler() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager=GridLayoutManager(this@MainActivity,2)
                adapter=dogAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter{dogAdapter.retry()},
                    footer = LoadingStateAdapter{dogAdapter.retry()}
                )
            }
        }
    }
}