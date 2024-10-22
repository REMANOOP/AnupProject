package com.example.nit3213project.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213project.model.Entity
import com.example.nit3213project.R

class EntityAdapter(private val entities: List<Entity>, private val onClick: (Entity) -> Unit) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistTextView: TextView = itemView.findViewById(R.id.artistTextView)
        val albumTitleTextView: TextView = itemView.findViewById(R.id.albumTitleTextView)
        val releaseYearTextView: TextView = itemView.findViewById(R.id.releaseYearTextView)
        val imageView: ImageView = itemView.findViewById(R.id.entityImageView) // New ImageView for the entity image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.artistTextView.text = entity.artistName
        holder.albumTitleTextView.text = entity.albumTitle
        holder.releaseYearTextView.text = entity.releaseYear.toString()
        holder.imageView.setImageResource(entity.imageResId) // Set the entity image

        holder.itemView.setOnClickListener {
            onClick(entity)
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
