package app.calc.signwithfirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreference:SharedPreferences
    private var sharedEmail = ""
    private var sharedPassword = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_regstration)

        sharedPreference = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        sharedEmail = sharedPreference.getString("email", "").toString()
        sharedPassword = sharedPreference.getString("password", "").toString()

        auth = Firebase.auth
        val goToLogin = findViewById<TextView>(R.id.goToLogin)
        val btnRegistration = findViewById<Button>(R.id.btnRegistration)

        goToLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegistration.setOnClickListener {
            val email = findViewById<EditText>(R.id.edtEmail)
            val password = findViewById<EditText>(R.id.edtPassword)
            val retryPassword = findViewById<EditText>(R.id.edtRetryPassword)

            val inputEmail = email.text.toString()
            val inputPassword = password.text.toString()
            val inputRetryPassword = retryPassword.text.toString()

            if (inputEmail.isEmpty() || inputPassword.isEmpty() || inputRetryPassword.isEmpty()) {
                email.error = "Please enter email"
                email.requestFocus()
                password.error = "Please enter password"
                password.requestFocus()
                retryPassword.error = "Please enter password"
                retryPassword.requestFocus()
            }
            if (inputPassword != inputRetryPassword){
                retryPassword.error = "Password not match"
                password.error = "Password not match"
                retryPassword.requestFocus()
            }
            if (inputPassword.length < 6){
                password.error = "Password must be at least 6 characters"
                password.requestFocus()
            }

            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                            sharedPreference.edit().putString("email", inputEmail).apply()
                            sharedPreference.edit().putString("password", inputPassword).apply()
                            val intent = Intent(this, SecretPage::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(this, "Registration failure", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}