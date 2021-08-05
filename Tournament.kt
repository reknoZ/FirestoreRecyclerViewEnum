package com.heewhack.firestorerecyclerview

import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable
import java.util.*

//@Serializable
enum class Gender { Female, Male }

//@Serializable
enum class Venue { Franchises, Évaux }

//@Serializable
enum class Category { Womens, Mens, Mixed }

//@Serializable
enum class Status { RegistrationOpen, RegistrationClosed, TournamentStarted, TournamentEnded, TournamentCanceled }

data class User (var id: String? = null,
				 var nameFirst: String = "", var nameLast: String = "",
				 var email: String = "",
				 var gender: Gender? = null,
				 var dob: Date? = null,

				 var profileImageURL: String? = null) // : java.io.Serializable

data class Team (
	var dateAdded: Date = Date(),
	var ranking: Int = 0,
	var players: ArrayList<String> = ArrayList()
) // : java.io.Serializable

data class Player (
	var id: String? = null,
	var userID: String = "",
	var dateAdded: Date = Date(),
	var gender: Gender = Gender.Female,
	var ranking: Int? = 0,
	var partner: String? = ""
) // : java.io.Serializable

data class Tournament (
	var season: String? = "2021",
	var date: Date? = Date(),
	var title: String? = "Tournoi de ",
	var venue: Venue? = Venue.Franchises,
	var category: Category? = Category.Mixed,
	var status: Status? = Status.RegistrationOpen,
	var maxNumberOfTeams: Int = 8,
	var includeInRankings: Boolean = true,
	var players: ArrayList<Player> = ArrayList(),
	var teams: ArrayList<Team> = ArrayList(),
) // : java.io.Serializable