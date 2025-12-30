package com.cgal.salesmanagement.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cgal.salesmanagement.data.model.RegisterRequest
import com.cgal.salesmanagement.data.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendingRequestsViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _requests = MutableLiveData<List<RegisterRequest>>()
    val requests: LiveData<List<RegisterRequest>> = _requests

    fun loadPendingRequests() {
        repository.getPendingRequests().enqueue(object : Callback<List<RegisterRequest>> {
            override fun onResponse(
                call: Call<List<RegisterRequest>>,
                response: Response<List<RegisterRequest>>
            ) {
                _requests.value = response.body()
            }

            override fun onFailure(call: Call<List<RegisterRequest>>, t: Throwable) {
                _requests.value = emptyList()
            }
        })
    }
}
