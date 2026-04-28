package ru.igormayachenkov.balk.data

sealed interface Calculation {
    data object Progress : Calculation
    data class Error(val error:String) : Calculation
    data class Success(
        val deflection: Double
    ):Calculation
}