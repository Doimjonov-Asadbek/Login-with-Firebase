package app.calc.signwithfirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreference:SharedPreferences
    private var sharedEmail = ""
    private var sharedPassword = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        sharedPreference = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        sharedEmail = sharedPreference.getString("email", "").toString()
        sharedPassword = sharedPreference.getString("password", "").toString()

        val goToRegistration = findViewById<TextView>(R.id.goToRegistration)
        val login = findViewById<Button>(R.id.btnLogin)

        login.setOnClickListener {
            performLogin()
        }

        goToRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun performLogin() {
        val email = findViewById<EditText>(R.id.edtEmail)
        val password = findViewById<EditText>(R.id.edtPassword)
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        val sharedPreference = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val sharedEmail = sharedPreference.getString("email", "").toString()
        val sharedPassword = sharedPreference.getString("password", "").toString()

        if (inputEmail == sharedEmail && inputPassword == sharedPassword) {
            val intent = Intent(this, SecretPage::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show()
        }
    }
}