package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class GetNotificationsResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("Notifications")
        var notifications: ArrayList<Notifications>?,
        @SerializedName("Error")
        var error: Error?

)

data class Notifications (

        @SerializedName("ID")
        var id: Long,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Type")
        var type: Long,
        @SerializedName("Description")
        var description: String,
        @SerializedName("DateUTC")
        var dateUTC: String,
        @SerializedName("IsRead")
        var isRead: Boolean,
        var isClicked: Boolean

) {
        override fun toString(): String {
                return "Notifications(id=$id, name='$name', description='$description', dateUTC='$dateUTC')"
        }
}