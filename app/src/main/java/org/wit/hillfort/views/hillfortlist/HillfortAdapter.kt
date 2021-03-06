package org.wit.hillfort.views.hillfortlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.wit.hillfort.R
import org.wit.hillfort.helpers.GlideApp
import org.wit.hillfort.models.HillfortModel

/**
 * Matthew O'Connor
 * 2019
 * Applied Computing
 */

interface HillfortListener {
  fun onHillfortClick(hillfort: HillfortModel)
}

class HillfortAdapter constructor(private var hillforts: List<HillfortModel>,
                                   private val listener: HillfortListener) : RecyclerView.Adapter<HillfortAdapter.MainHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_hillfort, parent, false))
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val hillfort = hillforts[holder.adapterPosition]
    holder.bind(hillfort, listener)
  }


  override fun getItemCount(): Int = hillforts.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hillfort: HillfortModel, listener: HillfortListener) {
      itemView.hillfortTitle.text = hillfort.title
      itemView.hillfortLocation.text = ("Location: lat-" + hillfort.location.lat + "  lng-" + hillfort.location.lng)
      itemView.hillfortVisited.isChecked = hillfort.visited
      itemView.rating.rating = hillfort.rating
      itemView.dateVisited.text = hillfort.date
      GlideApp.with(itemView.context).load(hillfort.image).error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(itemView.imageIcon);
      itemView.setOnClickListener { listener.onHillfortClick(hillfort) }
    }
  }
}