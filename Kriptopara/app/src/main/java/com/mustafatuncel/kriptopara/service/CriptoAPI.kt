package com.mustafatuncel.kriptopara.service

import com.mustafatuncel.kriptopara.model.CriptoModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CriptoAPI {
    //get post update delete
    // // https://api.nomics.com/v1/
    // prices?key=b66436bc1aac8fea1880f2617ebfcce2cb55258e
    @GET("prices?key=b66436bc1aac8fea1880f2617ebfcce2cb55258e")
    fun getdata():Call<List<CriptoModel>>
    // gelen veri list olarak ciripto model den gelecek

}