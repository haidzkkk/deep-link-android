package com.example.sso_matrix_android.data.model

data class IdentifyRequest(
    val type: String,
    val token: String,
    val initial_device_display_name: String = "",
    val device_id: String = ""
)