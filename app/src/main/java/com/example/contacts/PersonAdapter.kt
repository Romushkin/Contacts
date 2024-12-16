package com.example.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        private val itemCreatedAt: TextView = itemView.findViewById(R.id.itemCreatedAtTV)
        val itemIconDelete: ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

        fun bind(person: Person) {
            itemName.text = person.name
            itemPhone.text = person.phone

            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            itemCreatedAt.text = formatter.format(Date(person.createdAt))

            itemView.setOnClickListener {
                listener.onItemClicked(person)
            }
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