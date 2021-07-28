package com.mustafatuncel.kriptopara.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafatuncel.kriptopara.R
import com.mustafatuncel.kriptopara.adapter.RcylerViewAdapter
import com.mustafatuncel.kriptopara.model.CriptoModel
import com.mustafatuncel.kriptopara.service.CriptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RcylerViewAdapter.Listener{
    private val BASE_URL="https://api.nomics.com/v1/"
    private var cryptoModel:ArrayList<CriptoModel>?=null
    private var recylerViewAdapter:RcylerViewAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // https://api.nomics.com/v1/prices?key=b66436bc1aac8fea1880f2617ebfcce2cb55258e
        //API key: b66436bc1aac8fea1880f2617ebfcce2cb55258e

        //recylre view adapter
        val layoumanager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoumanager
        loadData()

    }
    private fun loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

       val service=retrofit.create(CriptoAPI::class.java)
       val call=service.getdata()

       call.enqueue(object :Callback<List<CriptoModel>> {
           override fun onResponse(
               call: Call<List<CriptoModel>>,
               response: Response<List<CriptoModel>>
           ) {
               if(response.isSuccessful){
                   response.body()?.let {
                       cryptoModel=ArrayList(it)
                       cryptoModel?.let {
                           recylerViewAdapter=RcylerViewAdapter(it,this@MainActivity)
                           recyclerView.adapter=recylerViewAdapter
                       }


                       /*
                       for(cryptomodels :CriptoModel in cryptoModel!!){
                        println(cryptomodels.currency)
                        println(cryptomodels.price)

                        */
                       }

                   }
               }


           override fun onFailure(call: Call<List<CriptoModel>>, t: Throwable) {
              t.printStackTrace()
           }

       })
    }

    override fun onItemClick(criptomodel: CriptoModel) {
       Toast.makeText(this,"Clicked:${criptomodel.currency}",Toast.LENGTH_LONG).show()
    }
}