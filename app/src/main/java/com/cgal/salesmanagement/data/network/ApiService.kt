package com.cgal.salesmanagement.data.network


import com.cgal.salesmanagement.data.model.ApiResponse
import com.cgal.salesmanagement.data.model.LoginRequest
import com.cgal.salesmanagement.data.model.LoginResponse
import com.cgal.salesmanagement.data.model.RegisterRequest
import com.cgal.salesmanagement.data.model.RegisterResponse
import com.cgal.salesmanagement.data.model.UserDetailsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/users/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/users")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET("/users")
    fun getPendingRequests(@Query("status") status: String): Call<List<RegisterRequest>>

    @GET("/users/{userId}")
    suspend fun getUserDetails(
        @Path("userId") userId: String
    ):  UserDetailsResponse

    @POST("/users/{userId}")
    suspend fun updateStatus(@Path("userId") userId: String, @Query("status") status: String): ApiResponse

}
