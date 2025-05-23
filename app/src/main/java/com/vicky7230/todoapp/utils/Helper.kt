package com.vicky7230.todoapp.utils

class Helper {

    fun isPalindrome(input: String): Boolean {
        var i = 0
        var j = input.length - 1
        while (i < j) {
            if (input[i] != input[j]) {
                return false
            }
            ++i
            --j
        }
        return true
    }

    fun isValidPassword(password: String): String {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 6 -> "Minimum password length should be 6"
            password.length > 15 -> "Maximum password length should be 15"
            else -> "Valid"
        }
    }

    fun reverseString(input: String?): String {
        if (input == null) {
            throw IllegalArgumentException("input string cannot be null")
        }
        var chars = input.toCharArray()
        var i = 0
        var j = input.length - 1
        while (i < j) {
            val temp = chars[i]
            chars[i] = chars[j]
            chars[j] = temp
            ++i
            --j
        }
        return chars.joinToString("")
    }
}