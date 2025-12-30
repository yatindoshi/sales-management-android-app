package com.cgal.salesmanagement.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgal.salesmanagement.data.model.ApiResponse
import com.cgal.salesmanagement.data.model.UserDetailsResponse
import com.cgal.salesmanagement.data.repository.AdminRepository
import kotlinx.coroutines.launch

class PendingRequestDetailsViewModel : ViewModel() {

    private val repository = AdminRepository()

    // -----------------------
    // User Details
    // -----------------------
    private val _userDetails = MutableLiveData<UserDetailsResponse>()
    val userDetails: LiveData<UserDetailsResponse> = _userDetails

    // -----------------------
    // Loading Indicator
    // -----------------------
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // -----------------------
    // Error Handling
    // -----------------------
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // -----------------------
    // Approve / Reject Result
    // -----------------------
    private val _actionResult = MutableLiveData<String>()
    val actionResult: LiveData<String> = _actionResult

    // -----------------------
    // Fetch User Details
    // -----------------------
    fun loadUserDetails(userId: String) {
        Log.d("PendingDetails", "loadUserDetails called")
        viewModelScope.launch {
            try {
                val response = repository.getUserDetails(userId)
                _userDetails.postValue(response)
            } catch (e: Exception) {

            }
        }
    }

    // -----------------------
    // Approve User
    // -----------------------
    fun approveUser(contact: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response: ApiResponse = repository.approveUser(contact)
                _actionResult.postValue("User approved successfully")
            } catch (e: Exception) {
                _error.value = e.message ?: "Approval failed"
            } finally {
                _loading.value = false
            }
        }
    }

    // -----------------------
    // Reject User
    // -----------------------
    fun rejectUser(contact: String) {

        _loading.value = true
        viewModelScope.launch {
            try {
                val response: ApiResponse = repository.rejectUser(contact)
                _actionResult.postValue("User rejected successfully")
            } catch (e: Exception) {
                _error.value = e.message ?: "Rejection failed"
            } finally {
                _loading.value = false
            }
        }
    }
}
