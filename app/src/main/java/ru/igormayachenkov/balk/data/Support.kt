package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable

@Serializable
enum class Support {
    SimplySupported,
    FixedHinged,
    FixedFixed
}