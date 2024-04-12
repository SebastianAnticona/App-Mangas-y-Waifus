package pe.edu.idat.sebastiananticona

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.sebastiananticona.databinding.ActivityNavigationBarBinding
import pe.edu.idat.sebastiananticona.databinding.DialogUsuarioBinding

class NavigationBar : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation_bar)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.imgbtnUser.setOnClickListener{
            MostrarUsuario()
        }

    }

    private fun MostrarUsuario(){
        val dialog = Dialog(this)
        val dialogUsuarioBinding = DialogUsuarioBinding.inflate(layoutInflater)
        dialog.setContentView(dialogUsuarioBinding.root)

        val bundle = intent.extras
        val emailInfo = bundle?.getString("email")
        dialogUsuarioBinding.etCorreo.text = emailInfo

        dialogUsuarioBinding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}