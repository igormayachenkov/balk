package ru.igormayachenkov.balk.domain

sealed interface Calculation {
    data object Progress : Calculation
    data class Error(val error:String) : Calculation
    data class Success(
        val deformation: Double
    ):Calculation
}
