package com.example.clean_meals.adapter



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_meals.R
import com.example.clean_meals.model.ScResponse
import com.example.clean_meals.model.School


class ScListAdapter(val items: ScResponse) : RecyclerView.Adapter<ScListAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvAdress = itemView.findViewById<TextView>(R.id.tv_Adress)

        fun bind(itemView: School){
            tvName.text = itemView.school_name
            Log.d("TAG", "학교 이름 ${itemView.school_name}")
            tvAdress.text = itemView.school_locate
            Log.d("TAG", "학교 주소 ${itemView.school_locate}")
        }
    }
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListner: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScListAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.sclistitem, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ScListAdapter.ViewHolder, position: Int) {
        holder.bind(items.data?.sc_list?.get(position)!!)
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return items.data?.sc_list?.size ?: 0
    }
}