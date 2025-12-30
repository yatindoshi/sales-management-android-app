package com.cgal.salesmanagement.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.cgal.salesmanagement.R
import com.cgal.salesmanagement.ui.dashboard.DashboardActivity
import com.cgal.salesmanagement.ui.register.RegisterActivity

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etUserType = findViewById<AutoCompleteTextView>(R.id.etUserType)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // --- Setup dropdown ---
        val userTypes = listOf("ADMIN", "EMPLOYEE")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, userTypes)
        etUserType.setAdapter(adapter)

        // Open dropdown when clicked
        etUserType.setOnClickListener {
            etUserType.showDropDown()
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val userType = etUserType.text.toString().trim()

            // --- Validation ---
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (userType.isEmpty()) {
                Toast.makeText(this, "Select user type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
            viewModel.login(email, password, userType)
        }

        viewModel.loginResult.observe(this, Observer { result ->
            result.fold(
                onSuccess = { response ->
                    val intent = Intent(this, DashboardActivity::class.java)

                    // Pass userType from API response
                    intent.putExtra("USER_TYPE", etUserType.text.toString().trim())

                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                },
                onFailure = {
                    Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            )
        })
    }
}
