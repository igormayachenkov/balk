package ru.igormayachenkov.balk.domain

import ru.igormayachenkov.balk.data.Balk

class CalculateUseCase(val balk: Balk) {
    operator fun invoke() : Calculation{
        return Calculation.Success(0.1 * balk.width)
    }
}