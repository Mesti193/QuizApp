package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class GetChatResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("Chats")
        var chats: ArrayList<Chat>?,
        @SerializedName("Error")
        var error: Error?

)

data class Chat(

        @SerializedName("ChatID")
        var chatID: Long,
        @SerializedName("StartedDateTime")
        var startedDateTime: String,
        @SerializedName("UserID")
        var userID: Long,
        @SerializedName("FirstName")
        var firstName: String,
        @SerializedName("LastName")
        var lastName: String,
        @SerializedName("Picture")
        var picture: String,
        @SerializedName("LastMessage")
        var lastMessage: String

)