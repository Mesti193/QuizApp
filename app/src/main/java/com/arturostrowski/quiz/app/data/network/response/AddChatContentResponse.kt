package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class AddChatContentResponse (

        @SerializedName("Success")
        var success: Boolean,
        @SerializedName("Error")
        var error: Error?

)