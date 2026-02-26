package ru.igormayachenkov.balk.domain

import ru.igormayachenkov.balk.data.Balk
import java.lang.Math.random

class CalculateUseCase() {
    operator fun invoke(balk: Balk) : Calculation{
        return Calculation.Success(random())
    }
}