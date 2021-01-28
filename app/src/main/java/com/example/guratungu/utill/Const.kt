package com.example.guratungu.utill

object Const {
    val PATH_COLLECTION = "users"
    val PATH_AGE = "intAge"

    fun setTimeStamp(): Long {
        val time = (-1 * System.currentTimeMillis())
        return time
    }

}