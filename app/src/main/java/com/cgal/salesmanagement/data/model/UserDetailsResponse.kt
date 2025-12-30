package com.cgal.salesmanagement.data.model

data class UserDetailsResponse(
    val name: String,
    val contact: String,
    val organization: String,
    val userType: String
)

data class ApiResponse(
    val success: Boolean,
    val message: String
)
