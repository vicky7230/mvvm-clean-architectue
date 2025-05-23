package com.vicky7230.todoapp.utils

class UserRepository {

    val users = listOf<User>(
        User(1, "John", "john@email.com", "123wer"),
        User(1, "Wein", "wein@email.com", "456wer"),
        User(1, "Emily", "emily@email.com", "789wer")
    )

    fun loginUser(email: String, password: String): LOGIN_STATUS {
        //fetch users from DB
        val users = users.filter { user -> user.email == email }
        return if (users.size == 1) {
            if (users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }
}