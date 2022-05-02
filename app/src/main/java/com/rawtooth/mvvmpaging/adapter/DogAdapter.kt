package com.rawtooth.mvvmpaging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rawtooth.mvvmpaging.R
import com.rawtooth.mvvmpaging.data.Dog
import com.rawtooth.mvvmpaging.databinding.EachRowBinding
import com.rawtooth.test.Breed
import javax.inject.Inject

class DogAdapter @Inject constructor() : PagingDataAdapter<Dog, DogAdapter.ViewHolder>(Diff) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = getItem(position)

        if (dog != null) {
            holder.bind(dog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EachRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(private val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val name=itemView.findViewById<TextView>(R.id.name)
        fun bind(dog: Dog) {
            binding.apply {
                image.load(dog.url)


            }
        }

    }

    object Diff : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean =
            oldItem.url == newItem.url
    }
}