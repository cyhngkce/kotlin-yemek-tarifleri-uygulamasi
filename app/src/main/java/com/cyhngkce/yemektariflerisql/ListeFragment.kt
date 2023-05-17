package com.cyhngkce.yemektariflerisql

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListeFragment : Fragment() {
    var yemekIsmiListesi=ArrayList<String>()
    var yemekIdListesi=ArrayList<Int>()
    private lateinit var listeAdapter:ListeRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView) // recyclerView'ı layout dosyasındaki ID ile ilişkilendirin
        listeAdapter = ListeRecyclerAdapter(yemekIsmiListesi, yemekIdListesi)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listeAdapter
        sqlVeriAlma()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }
    fun sqlVeriAlma(){
        try {
            activity?.let {
                val database=it.openOrCreateDatabase("Yemekler",Context.MODE_PRIVATE,null)
                val cursor=database.rawQuery("SELECT * FROM yemekler",null)
                val yemekIsmiIndex=cursor.getColumnIndex("yemekismi")
                val yemekIdIndex=cursor.getColumnIndex("id")
                yemekIsmiListesi.clear()
                yemekIdListesi.clear()
                while(cursor.moveToNext()){
                    yemekIsmiListesi.add(cursor.getString(yemekIsmiIndex))
                    yemekIdListesi.add(cursor.getInt(yemekIdIndex))
                }
                listeAdapter.notifyDataSetChanged()
                cursor.close()

            }

        }
        catch (e:Exception)
        {

        }
    }

}