package app.calc.signwithfirebase

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SecretPage : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences
    private var sharedEmail = ""
    private var sharedPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_secret_page)

        sharedPreference = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        sharedEmail = sharedPreference.getString("email", "").toString()
        sharedPassword = sharedPreference.getString("password", "").toString()

        Toast.makeText(this, "Email: $sharedEmail, Password: $sharedPassword", Toast.LENGTH_SHORT).show()

    }
}