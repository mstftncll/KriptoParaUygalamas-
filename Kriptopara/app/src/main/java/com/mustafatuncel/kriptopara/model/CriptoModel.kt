package com.mustafatuncel.kriptopara.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CriptoModel (
   // @SerializedName("currency")
    val currency:String,
    //@SerializedName("price")
    val price:String)
    //data sınıfı veri çekme sınıfı
    //constuctur olması lazım parantez içi
//Serialzed retrofit için
