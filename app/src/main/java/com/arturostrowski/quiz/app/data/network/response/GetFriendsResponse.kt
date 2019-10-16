package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName


data class GetFriendsResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("Friends")
        var friends: ArrayList<Friend>?,
        @SerializedName("FriendRequests")
        var friendRequests: ArrayList<Friend>?,
        @SerializedName("Error")
        var error: Error?

)

data class Friend(

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