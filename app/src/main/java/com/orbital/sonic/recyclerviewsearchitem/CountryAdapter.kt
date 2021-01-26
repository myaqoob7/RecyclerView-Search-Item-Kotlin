package com.orbital.sonic.recyclerviewsearchitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CountryAdapter(countryList: ArrayList<CountryItem>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var mCountryList: ArrayList<CountryItem> = countryList
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(v, mListener)
    }

    override fun getItemCount(): Int {
        return mCountryList.size
    }

    fun removeCountryItem(mPosition: Int){
        mCountryList.removeAt(mPosition)
        notifyItemRemoved(mPosition)
    }

    fun getCountryItem(mPosition: Int): CountryItem {
       return mCountryList[mPosition]
    }


    fun filterList(filteredList: ArrayList<CountryItem>) {
        mCountryList = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentItem: CountryItem = mCountryList[position]
        holder.mTextView.text = currentItem.countryName
    }

    class CountryViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.textView)
        var mDeleteImage: ImageView = itemView.findViewById(R.id.image_delete)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }

            mDeleteImage.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position)
                }
            }
        }

    }
}

