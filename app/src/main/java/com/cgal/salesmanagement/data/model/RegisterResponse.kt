package com.cgal.salesmanagement.data.model

data class RegisterResponse(
    val userId: String,
    val email: String,
    val name: String,
    val message: String
)
