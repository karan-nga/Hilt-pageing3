package com.rawtooth.mvvmpaging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rawtooth.mvvmpaging.databinding.ErrorStateBinding

class LoadingStateAdapter constructor(private val retry :()->Unit):LoadStateAdapter<LoadingStateAdapter.LoadViewHolder>() {


    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        return LoadViewHolder(ErrorStateBinding.inflate(LayoutInflater.from(parent.context),parent,false),retry)
    }
    class LoadViewHolder(private val binding: ErrorStateBinding,retry: () -> Unit):RecyclerView.ViewHolder(binding.root){
        init {
            binding.retryBtn.setOnClickListener{
                retry()
            }
        }
        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible=loadState is LoadState.Loading
                retryBtn.isVisible=loadState !is LoadState.Loading
                errorTxt.isVisible=loadState !is LoadState.Loading
            }
        }
    }
}