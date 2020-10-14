package com.example.clean_meals.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_meals.R
import com.example.clean_meals.model.School

class ScListAdapter(val memoList : ArrayList<School>) : RecyclerView.Adapter<ScListAdapter.Holder>(){

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val SchoolName = itemView.findViewById<TextView>(R.id.tv_name)
        val SchoolAdress = itemView.findViewById<TextView>(R.id.tv_Adress)
        fun bind(school: School) {
            Log.d("TAG", school.toString())
            SchoolName.text = school.school_name
            SchoolAdress.text = school.school_locate
            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sclistitem, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(memoList[position])
    }
}