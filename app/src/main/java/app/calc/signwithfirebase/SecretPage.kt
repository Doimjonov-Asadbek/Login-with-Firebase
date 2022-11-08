package app.calc.signwithfirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SecretPage : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences
    private var sharedEmail = ""
    private var sharedPassword = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_secret_page)

        sharedPreference = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        sharedEmail = sharedPreference.getString("email", "").toString()
        sharedPassword = sharedPreference.getString("password", "").toString()

        val LoginPage = findViewById<Button>(R.id.goToLogin)

        LoginPage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}