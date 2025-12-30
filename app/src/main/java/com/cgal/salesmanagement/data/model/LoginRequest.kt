package com.cgal.salesmanagement.data.model

data class LoginRequest(
    val email: String,
    val password: String,
    val userType: String
)
