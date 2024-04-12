package pe.edu.idat.sebastiananticona.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.sebastiananticona.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val informationViewModel =
            ViewModelProvider(this)[InformationViewModel::class.java]

        binding = FragmentInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        informationViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

}