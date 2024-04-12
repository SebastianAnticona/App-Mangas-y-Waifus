package pe.edu.idat.sebastiananticona.ui.mangas


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.idat.sebastiananticona.model.Manga

class MangasViewModel : ViewModel() {

    private lateinit var dbmangas : FirebaseFirestore
    val mangas: MutableLiveData<List<Manga>> = MutableLiveData()

    fun getMangas(){
        dbmangas = Firebase.firestore
        dbmangas.collection("Manga").orderBy("nombre_manga")
            .get()
            .addOnSuccessListener { result ->
                val mangaList = mutableListOf<Manga>()
                for (document in result){
                    val nombre = document.data["nombre_manga"].toString()
                    val estado = document.data["estado_manga"].toString()
                    val fuente = document.data["fuente_manga"].toString()

                    val manga = Manga(nombre,estado,fuente)
                    mangaList.add(manga)
                }
                mangas.postValue(mangaList)
            }
    }

    fun updateMangas(){
        val collectionmanga = dbmangas.collection("Manga").orderBy("nombre_manga")
        collectionmanga.addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            value?.let {
                val mangaList = mutableListOf<Manga>()
                for (document in it) {
                    val nombre: String = document.getString("nombre_manga")?: ""
                    val estado: String = document.getString("estado_manga")?: ""
                    val fuente: String = document.getString("fuente_manga")?: ""
                    val mangaAdded = Manga(nombre, estado, fuente)
                    mangaList.add(mangaAdded)
                }
                mangas.postValue(mangaList)
            }
        }
    }

}