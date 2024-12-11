package com.app.ecommerce.utils

import com.app.ecommerce.model.post

//NKHL_11
sealed class ApiState{
    object Loading:ApiState()
    class  Failure(val msg:Throwable):ApiState()
    class  Success(val data:List<post>):ApiState()
    object  Empty:ApiState()
}
