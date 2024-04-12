package pe.edu.idat.sebastiananticona


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.sebastiananticona.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Sign Up"


        binding.btnRegisterContinue.setOnClickListener {
            val email = binding.etRegisterEmailAddress.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Toast.makeText(this, "Registro realizado con exito", Toast.LENGTH_SHORT).show()
                            val mainActivity = Intent(this, MainActivity::class.java)
                            startActivity(mainActivity)
                            finish()
                        } else {
                            val alertBuilder = AlertDialog.Builder(this)
                                .setTitle("Ocurrio un error")
                                .setMessage("Se produjo un error con el registro del usuario")
                                .setPositiveButton("Aceptar", null)
                            val alert: AlertDialog = alertBuilder.create()
                            alert.show()
                        }
                    }
            }
            else{
                val alertBuilder = AlertDialog.Builder(this)
                    .setIcon(R.drawable.advertencia)
                    .setTitle("Advertercia")
                    .setMessage("El email address/password no pueden estar vacíos")
                    .setPositiveButton("Aceptar", null)
                val alert: AlertDialog = alertBuilder.create()
                alert.show()
                binding.etRegisterEmailAddress.text = null
                binding.etRegisterPassword.text = null
            }
        }
    }
}