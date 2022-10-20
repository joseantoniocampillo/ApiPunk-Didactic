package dev.campi.apipunksolution.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.databinding.BeerItemBinding

class BeerAdapter : ListAdapter<BeerType, BeerHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        val binding =
            BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        val detailedComicEntity = getItem(position)
        holder.bind(detailedComicEntity)
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<BeerType>() {
    override fun areItemsTheSame(oldItem: BeerType, newItem: BeerType): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BeerType, newItem: BeerType): Boolean =
        oldItem == newItem
}
