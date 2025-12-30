package com.cgal.salesmanagement.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cgal.salesmanagement.R
import com.cgal.salesmanagement.ui.login.LoginActivity
import android.widget.AutoCompleteTextView

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etOrganization = findViewById<EditText>(R.id.etOrganization)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etUserType = findViewById<AutoCompleteTextView>(R.id.etUserType)
        val userTypes = listOf("ADMIN", "EMPLOYEE")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, userTypes)
        etUserType.setAdapter(adapter)
        etUserType.setOnClickListener {
            etUserType.showDropDown()
        }
        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val userType = etUserType.text.toString().trim()
            val organization =etOrganization.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                registerViewModel.register(email, password, name, userType, organization)
            }
        }

        // Observe ViewModel result
        registerViewModel.registerResult.observe(this) { result ->
            result.onSuccess { response ->
                Toast.makeText(
                    this,
                    response.message ?: "Registration successful!",
                    Toast.LENGTH_SHORT
                ).show()

                // After success, go to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            result.onFailure { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
