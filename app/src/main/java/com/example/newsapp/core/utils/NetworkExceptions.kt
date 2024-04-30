package com.example.newsapp.core.utils

import com.example.newsapp.core.data.constants.EMPTY_RESPONSE_MSG
import com.example.newsapp.core.data.constants.UNKNOWN_ERROR

class EmptyResponseBodyException(override val message: String = EMPTY_RESPONSE_MSG) :
    Exception()

class UnknownApiErrorException(
    override val message: String = UNKNOWN_ERROR,
    val statusCode: Int
) : Exception()