package com.arturostrowski.quiz.app.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchResponse (

        @SerializedName("Name")
        var name: String,
        @SerializedName("battleStyleNo")
        var battleStyleNo: Long,
        @SerializedName("characterIndex")
        var characterIndex: Long,
        @SerializedName("guildName")
        var guildName: String,
        @SerializedName("guildNo")
        var guildNo: String,
        @SerializedName("level")
        var level: String,
        @SerializedName("nation")
        var nation: String,
        @SerializedName("serverNo")
        var serverNo: Long

)