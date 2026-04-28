package ru.igormayachenkov.balk.domain

import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.Calculation
import java.lang.Math.random

class CalculateUseCase() {
    operator fun invoke(balk: Balk) : Calculation {
        return Calculation.Success(random())
    }
}