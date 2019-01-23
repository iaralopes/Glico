package com.example.iaralopes.glico.core

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.base.OnItemClickListener
import com.example.iaralopes.glico.data.GlucoseEntity
import kotlinx.android.synthetic.main.item_historic.view.*

class HomeAdapter(
    var list: List<GlucoseEntity>,
    val listener: OnItemClickListener<GlucoseEntity>
) :
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.historicCategory.text = list[position].category
        holder.historicData.text = list[position].data
        holder.historicValue.text = list[position].value

        holder.historicColor.setBackgroundColor(getGlucoseColor(list[position].value.toInt()))

        holder.onClickView { listener.onItemClick(list[position], position) }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getGlucoseColor(glucoseValue: Int) : Int {
        return if (glucoseValue <= 70 || glucoseValue >= 180) {
            context.getColor(R.color.colorAlertGlucose)
        } else {
            context.getColor(R.color.colorNormalGlucose)
        }
    }


    class HomeViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var historicCategory = view.historic_category
        var historicData = view.historic_data
        var historicValue = view.historic_value
        var historicColor = view.historic_color

        fun onClickView(onItemClick : () -> Unit){
            view.delete_glucose.setOnClickListener { onItemClick()  }
        }

    }

}