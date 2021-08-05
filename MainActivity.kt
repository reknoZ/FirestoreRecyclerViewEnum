package com.heewhack.firestorerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
	private lateinit var recyclerView: RecyclerView
	private lateinit var tournamentsList: ArrayList<Tournament>
	private lateinit var tournamentAdapter: TournamentListAdapter
	private lateinit var db: FirebaseFirestore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		tournamentsList = arrayListOf()
		tournamentAdapter = TournamentListAdapter(tournamentsList)

		recyclerView = findViewById(R.id.recyclerView)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = tournamentAdapter

		EventChangeListener1()
	}

	private fun EventChangeListener1() {
		db = FirebaseFirestore.getInstance()
		db.collection("tournaments").addSnapshotListener { snapshot, error ->
			if (error != null) { return@addSnapshotListener }

			tournamentsList = snapshot!!.toObjects(Tournament::class.java) as ArrayList<Tournament>

			tournamentAdapter.notifyDataSetChanged()
		}
	}

	private fun EventChangeListener2() {
		db.collection("tournaments")
			.addSnapshotListener(object: EventListener<QuerySnapshot>{
				override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
					if (error != null) {
						Log.e ("Firestore error", error.message.toString())
						return
					}

					for (dc: DocumentChange in value?.documentChanges!!) {
						if (dc.type == DocumentChange.Type.ADDED) {
							tournamentsList.add(dc.document.toObject(Tournament::class.java))
						}
					}
					tournamentAdapter.notifyDataSetChanged()
				}
			})
	}
}
