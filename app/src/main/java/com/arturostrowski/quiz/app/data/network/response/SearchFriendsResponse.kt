package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchFriendsResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("SearchFriends")
        var friends: ArrayList<SearchFriends>?,
        @SerializedName("Error")
        var error: Error?

)

data class SearchFriends(

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
        var points: Long,
        @SerializedName("TypeID")
        var typeID: Long

)