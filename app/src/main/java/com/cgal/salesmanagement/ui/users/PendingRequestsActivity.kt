package com.cgal.salesmanagement.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgal.salesmanagement.R

class PendingRequestsActivity : ComponentActivity() {

    private val viewModel: PendingRequestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPendingRequests)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.requests.observe(this) { requests ->
            recyclerView.adapter = PendingRequestAdapter(
                requests
            ) { request ->
                Toast.makeText(
                    this,
                    "Viewing ${request.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.loadPendingRequests()
    }
}
