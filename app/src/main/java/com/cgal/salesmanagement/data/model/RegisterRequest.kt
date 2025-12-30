package com.cgal.salesmanagement.data.model

data class RegisterRequest(
    val contact: String,
    val password: String,
    val name: String,
    val userType: String,
    val organization: String
)
