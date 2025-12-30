package com.cgal.salesmanagement.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgal.salesmanagement.data.model.LoginRequest
import com.cgal.salesmanagement.data.model.LoginResponse
import com.cgal.salesmanagement.data.repository.AuthRepository

import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repo = AuthRepository()
    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult
    fun login(email: String, password: String, userType: String) {
        viewModelScope.launch {
            try {
                val response = repo.login(LoginRequest(email, password, userType))
                _loginResult.postValue(Result.success(response))
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            }
        }
    }
}
