package com.dikascode.beltexamtwo.model

import java.util.*

data class UserLocalDataModel(
    var id:Int = getAutoId(),
    var fName:String = "",
    var lName:String = "",
    var email:String = "",
    var city: String = "",
    var state:String = ""
) {
    companion object{
        fun getAutoId(): Int {
            val randomNumber = Random()
            return randomNumber.nextInt(100)
        }
    }
}
