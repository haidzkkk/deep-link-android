package com.example.sso_matrix_android.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sso_matrix_android.data.model.IdentifyRequest
import com.example.sso_matrix_android.data.model.IdentifyResponse
import com.example.sso_matrix_android.data.network.RetrofitBuilder
import com.example.sso_matrix_android.data.repo.MatrixRepo
import kotlinx.coroutines.async

class LoginViewModel (private val repo: MatrixRepo) : ViewModel(){

    val mutableList = MutableLiveData<IdentifyResponse>();

    fun testTokenAsync(token: String){
        viewModelScope.async {
            val data: IdentifyResponse = try{
                repo.apiClient.login(IdentifyRequest(
                    type = "m.login.token",
                    token = token
                ))
            }catch (e: Exception){
                IdentifyResponse(user_id = "Lỗi")
            }
            mutableList.value = data
        }
    }

}

class LoginViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api =  RetrofitBuilder.apiService;
        val repo = MatrixRepo(api)
        return modelClass.getConstructor(MatrixRepo::class.java).newInstance(repo)
    }
}