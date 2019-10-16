package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class GetLeaderboardResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("FriendsLeaderboard")
        var friendsLeaderboard: ArrayList<GlobalLeaderboard>?,
        @SerializedName("GlobalLeaderboard")
        var globalLeaderboard: ArrayList<GlobalLeaderboard>?,
        @SerializedName("Error")
        var error: Error?

)

data class GlobalLeaderboard (

        @SerializedName("UserID")
        var userID: Long,
        @SerializedName("FirstName")
        var firstName: String,
        @SerializedName("LastName")
        var lastName: String,
        @SerializedName("Picture")
        var picture: String,
        @SerializedName("Level")
        var level: Long,
        @SerializedName("Points")
        var points: Long

)
