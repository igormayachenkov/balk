package ru.igormayachenkov.balk.data

import kotlinx.serialization.Serializable

sealed interface Load {

    @Serializable
    data class PointLoad(
        val a : Double,
        val P : Double
    ): Load

    @Serializable
    data class UniformlyDistributedLoad(
        val w : Double,
    ): Load

    @Serializable
    data class TriangularDistributedLoad(
        val w : Double,
    ): Load

    @Serializable
    data class TrapezoidallyDistributedLoad(
        val wA : Double,
        val wB : Double,
    ): Load

    @Serializable
    data class PointMoment(
        val a : Double,
        val M : Double
    ): Load
}
