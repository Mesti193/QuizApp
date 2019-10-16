package com.arturostrowski.quiz.app.data.network.model

import com.google.gson.annotations.SerializedName

data class Item (

        @SerializedName("color")
        var color: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("descriptions")
        var descriptions: ArrayList<Descriptions>

)

data class Descriptions (

        @SerializedName("name")
        var name: String,
        @SerializedName("color")
        var color: String,
        @SerializedName("desc")
        var desc: String

)