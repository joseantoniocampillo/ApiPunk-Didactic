package dev.campi.apipunksolution.presentation.list

import androidx.recyclerview.widget.RecyclerView
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.databinding.BeerItemBinding

class BeerHolder(private val binding: BeerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(beer: BeerType) {
        "${beer.name} ${beer.description}".also { binding.tv.text = it }
    }
}