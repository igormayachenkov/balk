package ru.igormayachenkov.balk

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.igormayachenkov.balk.data.Balk
import ru.igormayachenkov.balk.data.Load
import ru.igormayachenkov.balk.data.Support
import ru.igormayachenkov.balk.domain.CalculateUseCase
import ru.igormayachenkov.balk.domain.Calculation

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Balk50x150x3000UnitTest {
    lateinit var calculation: Calculation.Success

    @Before
    fun calculate(){
        val balk = Balk(
            width  = 0.05,
            height = 0.15,
            length = 3.0,
            support = Support.SimplySupported,
            load = Load.PointLoad(0.5, 300.0)
        )
        println("*** balk: $balk")

        calculation = CalculateUseCase()(balk) as Calculation.Success
    }

    @Test
    fun deformation_isCorrect() {
        println("*** deformation: ${calculation.deflection}")
        assertEquals(0.005, calculation.deflection, 0.001 )
    }

}