package pe.edu.idat.sebastiananticona.ui.mangas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.idat.sebastiananticona.R
import pe.edu.idat.sebastiananticona.databinding.ActivityRegisterMangasBinding

class RegisterMangasActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterMangasBinding
    private lateinit var dbmanga : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterMangasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbmanga = Firebase.firestore
        title = "Agregar Manga"

        binding.btnAgregarManga.setOnClickListener{
            val nombre = binding.etNombreManga.text.toString()
            val estado = binding.etEstadoManga.text.toString()
            val fuente = binding.etFuenteManga.text.toString()
            if (nombre.isNotEmpty() && estado.isNotEmpty() && fuente.isNotEmpty()){
                val mangaData = hashMapOf(
                    "nombre_manga" to nombre,
                    "estado_manga" to estado,
                    "fuente_manga" to fuente,
                )
                dbmanga.collection("Manga")
                    .add(mangaData)
                    .addOnSuccessListener {
                        Log.d("Aggregate","Se agrego el manga con exito")
                    }
                    .addOnFailureListener{
                        Log.d("Aggregate","ERROR")
                    }
                Toast.makeText(this, "Manga Agregado Correctamente", Toast.LENGTH_SHORT).show()
            }else{
                val alertBuilder = AlertDialog.Builder(this)
                    .setIcon(R.drawable.advertencia)
                    .setTitle("Advertercia")
                    .setMessage("El nombre, estado o fuente estan vacíos")
                    .setPositiveButton("Aceptar", null)
                val alert: AlertDialog = alertBuilder.create()
                alert.show()
            }
            binding.etNombreManga.text = null
            binding.etEstadoManga.text = null
            binding.etFuenteManga.text = null
        }

    }
}