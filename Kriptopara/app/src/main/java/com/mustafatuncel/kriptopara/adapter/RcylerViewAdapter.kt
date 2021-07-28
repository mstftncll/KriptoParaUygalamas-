package com.mustafatuncel.kriptopara.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafatuncel.kriptopara.R
import com.mustafatuncel.kriptopara.model.CriptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RcylerViewAdapter(private val kriptolist:ArrayList<CriptoModel>,private val listener:Listener):RecyclerView.Adapter<RcylerViewAdapter.KriptoViewHolder>() {
    interface Listener{
        fun onItemClick(criptomodel :CriptoModel)
    }

   private val colors:Array<String> = arrayOf("#9D7EC2","#FFAA00","#FFDD00","#FF7E32","#FF7E32","#00FDFF","#0072FF","#FFFFA2")


    class KriptoViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(cryptomodel :CriptoModel,colors:Array<String>,position: Int,listener :Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptomodel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.kriptoname.text=cryptomodel.currency
            itemView.kriptofiyat.text=cryptomodel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KriptoViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return KriptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: KriptoViewHolder, position: Int) {
        holder.bind(kriptolist[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        return  kriptolist.count()
    }
}