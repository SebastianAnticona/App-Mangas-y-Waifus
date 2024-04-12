package pe.edu.idat.sebastiananticona.ui.waifus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.sebastiananticona.databinding.FragmentWaifusBinding

class WaifusFragment : Fragment() {

    private lateinit var binding: FragmentWaifusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWaifusBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setViews()

        val waifusViewModel =
            ViewModelProvider(this)[WaifusViewModel::class.java]
        waifusViewModel.waifus.observe(viewLifecycleOwner, Observer {
            binding.rvWaifus.adapter = RVWaifusAdapter(it!!)
            binding.pbWaifus.visibility = View.GONE
        })
        waifusViewModel.getWaifus()

        return root
    }

    private fun setViews() {
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvWaifus.layoutManager = linearManager
    }
}