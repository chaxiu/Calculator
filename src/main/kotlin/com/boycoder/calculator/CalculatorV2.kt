package com.boycoder.calculator

import kotlin.system.exitProcess


class CalculatorV2 {

    val exit = "exit"
    val help = """
--------------------------------------
使用说明：
1. 输入 1 + 1，按回车，即可使用计算器；
2. 注意：数字与符号之间要有空格；
3. 想要退出程序，请输入：exit
--------------------------------------""".trimIndent()

    fun start() {
        while (true) {
            println(help)

            val input = readLine() ?: continue
            val result = calculate(input)

            if (result == null) {
                println("输入格式不对")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    private fun calculate(input: String): String? {
        if (shouldExit(input)) exitProcess(0)

        val exp = parseExpression(input) ?: return null

        val left = exp.left
        val operator = exp.operator
        val right = exp.right

        return when (operator) {
            Operation.ADD -> addString(left, right)
            Operation.MINUS -> minusString(left, right)
            Operation.MULTI -> multiString(left, right)
            Operation.DIVI -> diviString(left, right)
        }
    }

    private fun addString(left: String, right: String): String {
        val result = left.toInt() + right.toInt()
        return result.toString()
    }

    private fun minusString(left: String, right: String): String {
        val result = left.toInt() - right.toInt()
        return result.toString()
    }

    private fun multiString(left: String, right: String): String {
        val result = left.toInt() * right.toInt()
        return result.toString()
    }

    private fun diviString(left: String, right: String): String {
        val result = left.toInt() / right.toInt()
        return result.toString()
    }

    private fun shouldExit(input: String): Boolean {
        return input == exit
    }

    private fun parseExpression(input: String): Expression? {
        val operation = parseOperator(input) ?: return null
        val list = input.split(operation.value)
        if (list.size != 2) return null
        return Expression(
            left = list[0].trim(),
            operator = operation,
            right = list[1].trim()
        )
    }

    private fun parseOperator(input: String): Operation? {
        Operation.values().forEach {
            if (input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    private fun parseOperator1(input: String): Operation? {
        return when {
            input.contains(Operation.ADD.value) -> Operation.ADD
            input.contains(Operation.MINUS.value) -> Operation.MINUS
            input.contains(Operation.MULTI.value) -> Operation.MULTI
            input.contains(Operation.DIVI.value) -> Operation.DIVI
            else -> null
        }
    }
}

enum class Operation(val value: String) {
    ADD("+"),
    MINUS("-"),
    MULTI("*"),
    DIVI("/")
}

data class Expression(
    val left: String,
    val operator: Operation,
    val right: String
)

fun main() {
    val calculator = CalculatorV2()
    calculator.start()
}

