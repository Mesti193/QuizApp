package com.arturostrowski.quiz.app.ui.leaderboard.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.GlobalLeaderboard
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface LeaderboardMVPView : MVPView {

    fun getUser(user: User)
    fun displayFriendsLeaderboard(friendsLeaderboard: ArrayList<GlobalLeaderboard>)
    fun displayGlobalLeaderboard(globalLeaderboard: ArrayList<GlobalLeaderboard>)
    fun displayError(error: String)

}