package com.dzhtv.rickandmortylibrary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dzhtv.rickandmortylibrary.databinding.ItemCharacterBinding
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.presentation.loadImage
import com.dzhtv.rickandmortylibrary.toLog
import javax.inject.Inject

class CharacterGridAdapter @Inject constructor():
    RecyclerView.Adapter<CharacterGridAdapter.CharacterViewHolder>() {

    private var items: MutableList<CharacterItem> = mutableListOf()

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    fun addItems(collections: List<CharacterItem>) {
        this.items.addAll(collections)
        notifyDataSetChanged()
    }

    fun refreshItems(collections: List<CharacterItem>) {
        this.items.apply {
            removeAll(this)
            addAll(collections)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val data = items.toList()[position]
        holder.binding.apply {
            title.text = data.name
            data.image.let { url ->
                imageView.loadImage(url)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}