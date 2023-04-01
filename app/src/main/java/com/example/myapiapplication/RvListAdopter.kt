package com.example.myapiapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import com.example.myapiapplication.network.Character

class RvListAdopter (val characterList :List<Character>) : RecyclerView.Adapter<RvListAdopter.ViewHolder>() {
    inner class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(character: Character){
            var name = itemView.findViewById<TextView>(R.id.rv_item_name)
            name.text =  character.name
            var image = itemView.findViewById<ImageView>(R.id.rv_item_img)
            image.load(character.img){
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(characterList[position])
    }
}