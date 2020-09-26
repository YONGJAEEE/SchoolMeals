package com.example.schoolmeals


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScListAdapter (val items : ScResponse) : RecyclerView.Adapter<ScListAdapter.ViewHolder>(){

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListner: ItemClickListener

    override fun getItemCount(): Int {
        return items.data?.sc_list?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.sclistitem, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.data?.sc_list?.get(position)!!)

    }

    inner class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {

        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvAdress = itemView.findViewById<TextView>(R.id.tv_Adress)

        fun bind(itemView: Sc_list) {
            tvName.text = itemView.school_name
            Log.d("TAG", "학교 이름 ${itemView.school_name}")
            tvAdress.text = itemView.address
            Log.d("TAG", "학교 주소 ${ itemView.address}")
        }

    }

}