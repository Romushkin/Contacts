package com.example.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private val context: Context, private val listener: PersonClickListener):
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    val persons = ArrayList<Person>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Person>){
        persons.clear()
        persons.addAll(newList)
        notifyDataSetChanged()

    }

    inner class PersonViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.itemNameTV)
        private val itemPhone: TextView = itemView.findViewById(R.id.itemPhoneTV)
        val itemIconDelete: ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

        fun bind(person: Person) {
            itemName.text = person.name
            itemPhone.text = person.phone

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val viewHolder =
            PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
        viewHolder.itemIconDelete.setOnClickListener{
            listener.onItemClicked(persons[viewHolder.adapterPosition])

        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentPerson = persons[position]
        holder.bind(currentPerson)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    interface PersonClickListener {
        fun onItemClicked(person: Person)
    }
}