package com.example.amantoliv2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amantoliv2.Model.Codes
import com.example.amantoliv2.R

class CodesAdapter (val codesList: List<Codes>):
    RecyclerView.Adapter<CodesAdapter.CodesCH>(){
    class CodesCH (itemView: View): RecyclerView.ViewHolder(itemView){

        var myCodesCheck: TextView = itemView.findViewById(R.id.tvCodeName)
        var myCodesOne: TextView = itemView.findViewById(R.id.tvCodeOne)
        var myCodesTwo: TextView = itemView.findViewById(R.id.tvCodeTwo)
        var myCodesThree: TextView = itemView.findViewById(R.id.tvCodeThree)
        var linearLayoutAdapter: LinearLayout = itemView.findViewById(R.id.llCardViewRow)
        var relativeLayoutAdapter: RelativeLayout = itemView.findViewById(R.id.rlExpandableCodes)

    }//Fin class CodesCH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodesCH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return CodesCH(view)
    }

    override fun getItemCount(): Int {
        return codesList.size
    }

    override fun onBindViewHolder(holder: CodesCH, position: Int) {

    }



}//Fin class CodesAdapter