package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class Error (

        @SerializedName("Code")
        var code: Long,
        @SerializedName("Message")
        var message: String

)