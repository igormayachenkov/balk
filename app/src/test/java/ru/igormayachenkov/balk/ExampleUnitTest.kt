package ru.igormayachenkov.balk

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    init {
        println("--- init ---")
    }

    @Before
    fun initTest(){
        println("=== Before ===")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        println("*** case addition ***")
    }

    @Test
    fun case2_isCorrect() {
        assertEquals(4, 2 + 2)
        println("*** case 2 ***")
    }

}