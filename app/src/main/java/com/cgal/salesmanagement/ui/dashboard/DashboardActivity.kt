package com.cgal.salesmanagement.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.cgal.salesmanagement.R
import com.cgal.salesmanagement.ui.salesman.ViewPendingRequestsActivity
import com.cgal.salesmanagement.ui.reports.SalesReportActivity
import com.cgal.salesmanagement.ui.stock.AddStockActivity
import com.cgal.salesmanagement.ui.stock.ViewStockActivity
import com.cgal.salesmanagement.ui.users.PendingRequestsActivity

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userType = intent.getStringExtra("USER_TYPE") ?: ""

        if (userType != "ADMIN") {
            Toast.makeText(this, "Access Denied — Only Admin Can Login", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val btnViewSalesman = findViewById<Button>(R.id.btnPendingRequests)
        val btnSellReport = findViewById<Button>(R.id.btnSellReport)
        val btnAddStock = findViewById<Button>(R.id.btnAddStock)
        val btnViewStock = findViewById<Button>(R.id.btnViewStock)

        btnViewSalesman.setOnClickListener {
            startActivity(Intent(this, PendingRequestsActivity::class.java))
        }

        btnSellReport.setOnClickListener {
            startActivity(Intent(this, SalesReportActivity::class.java))
        }

        btnAddStock.setOnClickListener {
            startActivity(Intent(this, AddStockActivity::class.java))
        }

        btnViewStock.setOnClickListener {
            startActivity(Intent(this, ViewStockActivity::class.java))
        }
    }
}
