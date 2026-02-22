package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable


/**
 * The Balk parameters
 */
@Serializable
data class Balk(
    val width : Double,
    val height: Double,
    val length: Double,
    val support: Support,
    val load: Load
)
