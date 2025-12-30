package com.cgal.salesmanagement.data.repository

import com.cgal.salesmanagement.data.model.LoginRequest
import com.cgal.salesmanagement.data.model.LoginResponse
import com.cgal.salesmanagement.data.model.PendingRequests
import com.cgal.salesmanagement.data.model.RegisterRequest
import com.cgal.salesmanagement.data.model.RegisterResponse
import com.cgal.salesmanagement.data.network.ApiService
import com.cgal.salesmanagement.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthRepository {

    suspend fun login(request: LoginRequest) : LoginResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081") // Emulator → Spring Boot running on localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        return api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        // Replace with Retrofit/HTTP call to Spring Boot backend

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081") // Emulator → Spring Boot running on localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        return api.register(request)
    }

    fun getPendingRequests(): Call<List<RegisterRequest>> {
        // Replace with Retrofit/HTTP call to Spring Boot backend

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081") // Emulator → Spring Boot running on localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        return api.getPendingRequests("NEW");
    }
}
