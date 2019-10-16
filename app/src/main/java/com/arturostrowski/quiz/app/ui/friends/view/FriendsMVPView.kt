package com.arturostrowski.quiz.app.ui.friends.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Friend
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface FriendsMVPView : MVPView {

    fun getUser(user: User)
    fun reloadFriends()
    fun displayFriends(friends: ArrayList<Friend>)
    fun displayFriendRequests(friendRequests: ArrayList<Friend>)
    fun displayError(error: String)

}