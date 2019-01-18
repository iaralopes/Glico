package com.example.iaralopes.glico.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.data.GlucoseEntity
import kotlinx.android.synthetic.main.item_historic.view.*

class HomeAdapter (val list: List<GlucoseEntity>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        return HomeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_historic, parent, false)
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.historicCategory.text = list[position].category
        holder.historicData.text = list[position].data
        holder.historicValue.text = list[position].value
    }


    class HomeViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var historicCategory = view.historic_category
        var historicData = view.historic_data
        var historicValue = view.historic_value


    }

}