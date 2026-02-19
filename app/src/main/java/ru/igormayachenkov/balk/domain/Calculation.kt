package ru.igormayachenkov.balk.domain

sealed interface Calculation {
    data class Error(val error:String) : Calculation
    data class Success(
        val deformation: Double
    ):Calculation
}
