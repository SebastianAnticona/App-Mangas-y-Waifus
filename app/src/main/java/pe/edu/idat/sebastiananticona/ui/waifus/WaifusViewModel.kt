package pe.edu.idat.sebastiananticona.ui.waifus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.idat.sebastiananticona.model.Waifu
import pe.edu.idat.sebastiananticona.retrofit.WaifusRepository

class WaifusViewModel : ViewModel() {

    val waifus: MutableLiveData<List<Waifu>> = MutableLiveData()
    fun getWaifus() {
        CoroutineScope(Dispatchers.IO).launch{
            val Waifus = WaifusRepository.apiWaifus.list_Waifus()
            withContext(Dispatchers.Main) {
                waifus.postValue(Waifus.waifu)
            }
        }
    }
}