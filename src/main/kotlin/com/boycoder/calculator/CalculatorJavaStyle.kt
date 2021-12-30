package com.boycoder

import kotlin.system.exitProcess

fun main() {
    while(true) {
        println("请输入标准的算式，并且按回车; \n" +
                "比如：1 + 1，注意符合与数字之间要有空格。\n" +
                "输入exit，退出程序。")

        var input = readLine()
        if (input == null) continue
        if (input == "exit") exitProcess(0)

        var inputList = input.split(" ")
        var result = calculate(inputList)

        if (result == null) {
            println("输入格式不对")
            continue
        } else {
            println("$input = $result")
        }
    }
}

private fun calculate(inputList: List<String>): Int? {
    if (inputList.size != 3) return null

    var left = inputList.get(0).toInt()
    var operation = inputList.get(1)
    var right = inputList.get(2).toInt()

    when(operation) {
        "+" -> return left + right
        "-" -> return left - right
        "*" -> return left * right
        "/" -> return left / right
        else -> return null
    }
}