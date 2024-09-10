package com.example.contextmonitoring

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiseaseRatingAdapter(
    private val context: Context,
    private val diseaseList: List<DiseaseRating>
) : RecyclerView.Adapter<DiseaseRatingAdapter.DiseaseRatingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseRatingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_disease_rating, parent, false)
        return DiseaseRatingViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiseaseRatingViewHolder, position: Int) {
        val diseaseRating = diseaseList[position]
        holder.diseaseNameTextView.text = diseaseRating.diseaseName
        holder.diseaseRatingBar.rating = diseaseRating.rating

        holder.diseaseRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            diseaseRating.rating = rating
        }
    }

    override fun getItemCount(): Int = diseaseList.size

    class DiseaseRatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val diseaseNameTextView: TextView = view.findViewById(R.id.diseaseNameTextView)
        val diseaseRatingBar: RatingBar = view.findViewById(R.id.diseaseRatingBar)
    }
}
