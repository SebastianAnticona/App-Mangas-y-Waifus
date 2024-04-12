package pe.edu.idat.sebastiananticona.ui.waifus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.sebastiananticona.databinding.ItemWaifusBinding
import pe.edu.idat.sebastiananticona.model.Waifu

class RVWaifusAdapter(var waifus: List<Waifu>): RecyclerView.Adapter<RVWaifusAdapter.VHWaifu>() {
    class VHWaifu(private val binding: ItemWaifusBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(waifu: Waifu) {
            binding.tvArtistName.text = waifu.nombre_artista
            binding.tvArtistUrlPage.text = waifu.url_pagina_artista
            Glide
                .with(binding.root.context)
                .load(waifu.url_imagen)
                .centerCrop()
                .into(binding.ivWaifu);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHWaifu {
        val binding = ItemWaifusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHWaifu(binding)
    }

    override fun onBindViewHolder(holder: VHWaifu, position: Int) {
        holder.bind(waifus[position])
    }

    override fun getItemCount(): Int = waifus.size
}