package pe.edu.idat.sebastiananticona.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "App para listar mangas y mostrar personajes femeninos de anime(Waifus) " +
                "creada por Sebastian Miguel Anticona Fiestas"
    }
    val text: LiveData<String> = _text
}