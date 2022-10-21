package com.dikascode.beltexamtwo.controller


import com.dikascode.beltexamtwo.R
import com.dikascode.beltexamtwo.base_interface.BaseView
import com.dikascode.beltexamtwo.model.UserData
import com.dikascode.beltexamtwo.network.RetroInstance
import com.dikascode.beltexamtwo.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataController(private val view: BaseView) {

    private val retroInstance: RetroService =
        RetroInstance.getRetroInstance().create(RetroService::class.java)


    fun getUserData() {
        val apiCall = retroInstance.getRandomUserData()
        view.showLoading(true)

        apiCall.enqueue(object : Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if(response.code() == 200 || response.isSuccessful) {
                    response.body()?.let { view.initRecyclerView(it) }
                    view.showToast(R.string.data_fetch_success)
                }else{
                    view.showToast(R.string.data_fetch_failed)
                }
                view.showLoading(false)
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                view.showLoading(false)
                view.showToast(R.string.data_fetch_failed)
            }

        })
    }
}