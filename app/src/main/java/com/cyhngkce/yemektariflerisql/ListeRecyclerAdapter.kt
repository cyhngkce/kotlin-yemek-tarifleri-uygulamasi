package com.cyhngkce.yemektariflerisql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation

class ListeRecyclerAdapter(val yemekListesi: ArrayList<String>, val idListesi: ArrayList<Int>) :
    RecyclerView.Adapter<ListeRecyclerAdapter.YemekHolder>() {

    class YemekHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerRowText: AppCompatTextView = itemView.findViewById(R.id.recycler_row_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)

        return YemekHolder(view)
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
        holder.recyclerRowText.text = yemekListesi[position]
holder.itemView.setOnClickListener{
    val action=ListeFragmentDirections.actionListeFragmentToTarifFragment("recyclerdangelis",idListesi[position])
  Navigation.findNavController(it).navigate(action)
}
    }
}