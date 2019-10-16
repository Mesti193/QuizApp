package com.arturostrowski.quiz.app.ui.leaderboard.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.leaderboard.interactor.LeaderboardMVPInteractor
import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardMVPView

interface LeaderboardMVPPresenter<V : LeaderboardMVPView, I : LeaderboardMVPInteractor> : MVPPresenter<V, I> {

    fun onLeaderboardPrepared(user: User)
    fun loadUserFromDatabase()

}