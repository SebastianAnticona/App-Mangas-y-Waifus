package pe.edu.idat.sebastiananticona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import pe.edu.idat.sebastiananticona.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogIn.setOnClickListener{
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
        }

        binding.btnSignUp.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }

        binding.btnLogInGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.CLIENT_ID))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            activityResultLauncher.launch(googleClient.signInIntent)
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account!=null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                            result ->
                        if (result.isSuccessful) {
                            Toast.makeText(this, "Inicio de sesión de Google con exito", Toast.LENGTH_SHORT).show()
                            val navigationBar = Intent(this, NavigationBar::class.java)
                            navigationBar.putExtra("email",account.email)
                            startActivity(navigationBar)
                            finish()
                        } else {
                            val alertBuilder = AlertDialog.Builder(this)
                                .setIcon(R.drawable.error)
                                .setTitle("Ocurrio un error")
                                .setMessage("Se produjo un error con la autenticacion del usuario")
                                .setPositiveButton("Aceptar", null)
                            val alert: AlertDialog = alertBuilder.create()
                            alert.show()
                        }
                    }
                } else {
                    val alertBuilder = AlertDialog.Builder(this)
                        .setIcon(R.drawable.advertencia)
                        .setTitle("Advertercia")
                        .setMessage("No puede dejar nada vacio")
                        .setPositiveButton("Aceptar", null)
                    val alert: AlertDialog = alertBuilder.create()
                    alert.show()
                }
            } catch (e: ApiException) {
                val alertBuilder = AlertDialog.Builder(this)
                    .setIcon(R.drawable.error)
                    .setTitle("Ocurrio un error")
                    .setMessage("No se selecciono alguna cuenta de Google")
                    .setPositiveButton("Aceptar", null)
                val alert: AlertDialog = alertBuilder.create()
                alert.show()
            }
        }
    }
}