package com.boycoder.calculator

import kotlin.test.Test
import kotlin.test.assertEquals

class TestCalculatorV3 {
    @Test
    fun testCalculate() {
        val calculator = CalculatorV3()

        val res1 = calculator.calculate("2333333333333332+1")
        assertEquals("2333333333333333", res1)

    }
}