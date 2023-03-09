package com.example.gameofthrones

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.DataModelItem
import com.example.gameofthrones.databinding.ItemDisplayBinding
import com.squareup.picasso.Picasso

class DataAdapter() : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    //data that we are receiving
    private var characters: ArrayList<DataModelItem>? = null

    //view holder class
    inner class MyViewHolder(val binding: ItemDisplayBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDisplayBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            characterFirstName.text = characters?.get(position)?.firstName
            characterFullName.text = characters?.get(position)?.fullName
            characterTitle.text = characters?.get(position)?.title
            Picasso.get().load(characters?.get(position)?.imageUrl).placeholder(R.drawable.ic_launcher_foreground).into(imageCharacter)
        }
    }

    override fun getItemCount(): Int {
        return characters?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characters: ArrayList<DataModelItem>?) {
        this.characters = characters
        notifyDataSetChanged()
    }

    fun addData(character: DataModelItem) {
        characters?.let {
            it.add(character)
            notifyItemChanged(it.size-1)
        }
    }

}



