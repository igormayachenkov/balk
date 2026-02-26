package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable


/**
 * The Balk parameters
 */
@Serializable
data class Balk(
    val form    : Form,
    val support : Support,
    val load    : Load
)
