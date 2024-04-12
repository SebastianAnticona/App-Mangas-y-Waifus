package pe.edu.idat.sebastiananticona


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.sebastiananticona.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Log In"



        binding.btnLoginContinue.setOnClickListener {
            val email = binding.etLoginEmailAddress.text.toString()
            val password = binding.etLoginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Toast.makeText(this, "Inicio de sesión con exito", Toast.LENGTH_SHORT).show()
                            val navigationBar = Intent(this, NavigationBar::class.java)
                            navigationBar.putExtra("email",email)
                            startActivity(navigationBar)
                            finish()
                        } else {
                            val alertBuilder = AlertDialog.Builder(this)
                                .setIcon(R.drawable.error)
                                .setTitle("Ocurrio un error")
                                .setMessage("Se produjo un error con la autenticacion")
                                .setPositiveButton("Aceptar", null)
                            val alert: AlertDialog = alertBuilder.create()
                            alert.show()
                            binding.etLoginEmailAddress.text = null
                            binding.etLoginPassword.text = null
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
                binding.etLoginEmailAddress.text = null
                binding.etLoginPassword.text = null
            }
        }
    }
}