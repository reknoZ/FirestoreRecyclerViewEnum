package com.heewhack.firestorerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TournamentListAdapter (private var tournamentList: ArrayList<Tournament>) : RecyclerView.Adapter<TournamentListAdapter.TournamentViewHolder>() {

	class TournamentViewHolder (view: View) : RecyclerView.ViewHolder (view) {
		val titleTV: TextView = view.findViewById(R.id.tournamenTitle)
		val dateTV: TextView = view.findViewById(R.id.tournamenDate)
		val venueTV: TextView = view.findViewById(R.id.tournamenVenue)
	}

	override fun getItemCount() : Int {
		return tournamentList.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return TournamentViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
		val tournament = tournamentList[position]

		holder.titleTV.text = tournament.title
		holder.venueTV.text = tournament.venue.toString()
		holder.dateTV.text = tournament.date.toString()
	}
}
