package com.cgal.salesmanagement.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.cgal.salesmanagement.R
import android.util.Log
import com.cgal.salesmanagement.ui.dashboard.DashboardActivity

class PendingRequestDetailsActivity : ComponentActivity() {

    private val viewModel: PendingRequestDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("PendingDetails", "PendingRequestDetailsActivity onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_request_details)

        // -------- Views --------
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvContact = findViewById<TextView>(R.id.tvContact)
        val tvOrganization = findViewById<TextView>(R.id.tvOrganization)
        val tvUserType = findViewById<TextView>(R.id.tvUserType)
        val btnApprove = findViewById<Button>(R.id.btnApprove)
        val btnReject = findViewById<Button>(R.id.btnReject)

        // -------- Get userId --------
        val userId = intent.getStringExtra("USER_ID")
        Log.d("PendingDetails", "Received USER_ID = $userId")

        if (userId.isNullOrBlank()) {
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 🔥 THIS IS THE KEY LINE 🔥
        viewModel.loadUserDetails(userId)

        // -------- Observe user details --------
        viewModel.userDetails.observe(this, Observer { user ->
            user ?: return@Observer

            tvName.text = user.name
            tvContact.text = user.contact
            tvOrganization.text = user.organization
            tvUserType.text = user.userType
        })

        // -------- Observe approve/reject --------
        viewModel.actionResult.observe(this) { message ->
            message ?: return@observe

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            // Go back to Dashboard
            val intent = Intent(this, PendingRequestsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // -------- Buttons --------
        btnApprove.setOnClickListener {
            viewModel.approveUser(userId)
        }

        btnReject.setOnClickListener {
            viewModel.rejectUser(userId)
        }
    }
}
