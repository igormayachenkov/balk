package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable

@Serializable
enum class LoadType {

    CenterPoint,
    Distributed

}

@Serializable
data class Load(
    val loadType: LoadType,
    val weight  : Double
)
