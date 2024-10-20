package com.example.nit3213project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(private val entities: List<Entity>, private val onClick: (Entity) -> Unit) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistTextView: TextView = itemView.findViewById(R.id.artistTextView) // Updated
        val albumTitleTextView: TextView = itemView.findViewById(R.id.albumTitleTextView) // Updated
        val releaseYearTextView: TextView = itemView.findViewById(R.id.releaseYearTextView) // New
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.artistTextView.text = entity.artistName  // Bind the artist name
        holder.albumTitleTextView.text = entity.albumTitle  // Bind the album title
        holder.releaseYearTextView.text = entity.releaseYear.toString() // Bind the release year

        holder.itemView.setOnClickListener {
            onClick(entity)  // Pass the entity to the onClick function in DashboardActivity
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
