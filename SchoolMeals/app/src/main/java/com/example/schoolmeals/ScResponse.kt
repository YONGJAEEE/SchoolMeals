package com.example.schoolmeals



data class ScResponse(
    val status: Int,
    val message: String,
    val data: Data?
)

data class Data(
    val sc_list: List<Sc_list?>?
)

data class Sc_list(
    val school_name: String?,
    val a_sc_code: String?,
    val sc_code: String?,
    val address: String?
)