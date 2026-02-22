package ru.igormayachenkov.balk.domain

import ru.igormayachenkov.balk.data.Balk

class CalculateUseCase() {
    operator fun invoke(balk: Balk) : Calculation{
        return Calculation.Success(0.1 * balk.width)
    }
}