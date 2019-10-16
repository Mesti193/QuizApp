package com.arturostrowski.quiz.app.ui.find_friends.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.SearchFriends
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface FindFriendsMVPView : MVPView {

    fun getUser(user: User)
    fun removeMoreMenu()
    fun displayFriends(friends: ArrayList<SearchFriends>)
    fun displayError(error: String)

}