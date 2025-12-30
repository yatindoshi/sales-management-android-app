package com.cgal.salesmanagement.data.repository

import com.cgal.salesmanagement.data.network.RetrofitClient

class AdminRepository {

    private val api = RetrofitClient.instance

    suspend fun getUserDetails(userId: String) =
        api.getUserDetails(userId)

    suspend fun approveUser(userId: String) =
        api.updateStatus(userId,"APPROVED")

    suspend fun rejectUser(userId: String) =
        api.updateStatus(userId,"DENIED")
}
