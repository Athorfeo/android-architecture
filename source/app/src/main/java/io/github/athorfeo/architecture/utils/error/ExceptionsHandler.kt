package io.github.athorfeo.architecture.utils.error

import java.net.UnknownHostException

fun Throwable.getCode(): Int{
    var code = AppCode.DEFAULT

    when(this){
        is UnknownHostException -> { code = AppCode.NO_INTERNET }
    }

    return code
}