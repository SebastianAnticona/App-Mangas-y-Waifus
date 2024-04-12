package pe.edu.idat.sebastiananticona.ui.mangas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.sebastiananticona.databinding.ItemMangasBinding
import pe.edu.idat.sebastiananticona.model.Manga

class RVMangasAdapter(var mangas: List<Manga>): RecyclerView.Adapter<RVMangasAdapter.VHManga>() {
    class VHManga(private val binding: ItemMangasBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Manga) {
            binding.tvNombreManga.text = manga.nombre_manga
            binding.tvEstadoManga.text = manga.estado_manga
            binding.tvFuenteManga.text = manga.fuente_manga
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHManga {
        val binding = ItemMangasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHManga(binding)
    }

    override fun onBindViewHolder(holder: VHManga, position: Int) {
        holder.bind(mangas[position])
    }

    override fun getItemCount(): Int = mangas.size
}