package com.dzhtv.rickandmortylibrary.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dzhtv.rickandmortylibrary.data.GlideApp
import com.dzhtv.rickandmortylibrary.databinding.ItemCharacterBinding
import com.dzhtv.rickandmortylibrary.data.model.Character
import javax.inject.Inject


class CharacterGridAdapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<CharacterGridAdapter.CharacterViewHolder>() {

    private var items: MutableList<Character> = mutableListOf()

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    fun refreshItems(collections: List<Character>) {
        this.items.addAll(collections)
        notifyDataSetChanged()
    }

    fun addItems(collections: List<Character>) {
        this.items.apply {
            removeAll(this)
            addAll(collections)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val data = items.toList()[position]
        holder.binding.title.text = data.name
        data.image?.let { url ->
            GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(holder.binding.imageView)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}