package com.example.sso_matrix_android.data.network
import com.example.sso_matrix_android.data.model.IdentifyRequest
import com.example.sso_matrix_android.data.model.IdentifyResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/_matrix/client/r0/login")
    suspend fun login(@Body identify: IdentifyRequest): IdentifyResponse
}