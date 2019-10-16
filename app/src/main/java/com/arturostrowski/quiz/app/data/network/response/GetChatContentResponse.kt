package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class GetChatContentResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("Timestamp")
        var timestamp: Long,
        @SerializedName("ChatContent")
        var chatContent: ArrayList<ChatContent>?,
        @SerializedName("User")
        var user: UserDetail,
        @SerializedName("Error")
        var error: Error?

)

data class ChatContent(

        @SerializedName("ChatContentID")
        var chatContentID: Long,
        @SerializedName("UserID")
        var userID: Long,
        @SerializedName("Message")
        var message: String,
        @SerializedName("DateTime")
        var dateTime: String

)

data class UserDetail(

        @SerializedName("UserID")
        var userID: Long,
        @SerializedName("FirstName")
        var firstName: String,
        @SerializedName("LastName")
        var lastName: String,
        @SerializedName("Picture")
        var picture: String

)