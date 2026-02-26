package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable

@Serializable
sealed interface Form {

    @Serializable
    data class Rectangle(
        val width : Double,
        val height: Double,
        val length: Double,
    ) : Form

    @Serializable
    data class Circle(
        val radius: Double,
        val length: Double,
    ) : Form

}