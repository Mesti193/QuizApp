package com.arturostrowski.quiz.app.data.network.request

data class SearchRequest (
        var nationCode: String = "eu",
        var characterName: String
) {
    fun getQueryMap(): Map<String, String> {
        val q: MutableMap<String, String> = mutableMapOf()
        q.put("nationCode", nationCode)
        q.put("characterName", characterName)
        return q
    }
}