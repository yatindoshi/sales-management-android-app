package com.cgal.salesmanagement.ui.register



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgal.salesmanagement.data.model.RegisterRequest
import com.cgal.salesmanagement.data.model.RegisterResponse
import com.cgal.salesmanagement.data.repository.AuthRepository
import kotlinx.coroutines.launch


class RegisterViewModel(
    private val repository: AuthRepository = AuthRepository() // inject repo in real apps
) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    fun register(email: String, password: String, name: String, userType: String, organization: String) {
        viewModelScope.launch {
            try {
                val response = repository.register(
                    RegisterRequest(email, password, name, userType, organization)
                )
                _registerResult.postValue(Result.success(response))
            } catch (e: Exception) {
                _registerResult.postValue(Result.failure(e))
            }
        }
    }
}
