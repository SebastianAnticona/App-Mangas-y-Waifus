package pe.edu.idat.sebastiananticona.ui.mangas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import pe.edu.idat.sebastiananticona.databinding.FragmentMangasBinding

class MangasFragment : Fragment() {

    private lateinit var binding: FragmentMangasBinding
    private lateinit var dbmanga : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMangasBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val mangasViewModel =
            ViewModelProvider(this)[MangasViewModel::class.java]
        binding.rvMangas.layoutManager = LinearLayoutManager(activity)
        mangasViewModel.mangas.observe(viewLifecycleOwner,Observer {
            binding.rvMangas.adapter = RVMangasAdapter(it!!)
            for ( i in 1..1000000){
                binding.pbMangas.visibility = View.VISIBLE
            }
            binding.pbMangas.visibility = View.GONE
        })

        mangasViewModel.getMangas()


        binding.fabtnAgregar.setOnClickListener{
            val registerMangasActivity = Intent(context,RegisterMangasActivity::class.java)
            startActivity(registerMangasActivity)
        }


        binding.fabtnActualizar.setOnClickListener{
            mangasViewModel.updateMangas()
        }

        return root
    }

}


