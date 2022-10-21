package com.dikascode.beltexamtwo.base_interface

import androidx.annotation.StringRes
import com.dikascode.beltexamtwo.model.UserData

interface BaseView {
    fun showToast(@StringRes message: Int)

    fun initRecyclerView(usersList: UserData) {

    }

    fun showLoading(state: Boolean) {

    }

}