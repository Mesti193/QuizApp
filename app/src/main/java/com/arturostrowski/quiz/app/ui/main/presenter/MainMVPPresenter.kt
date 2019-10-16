package com.arturostrowski.quiz.app.ui.main.presenter

import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.main.interactor.MainMVPInteractor
import com.arturostrowski.quiz.app.ui.main.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {

    fun onDrawerOptionChatClick() : Unit?
    fun onDrawerOptionFindFriendsClick() : Unit?
    fun onDrawerOptionFriendsClick() : Unit?
    fun onDrawerOptionNotificationsClick() : Unit?
    fun onDrawerOptionLeaderboardClick() : Unit?
    fun onDrawerOptionSettingsClick() : Unit?
    fun onDrawerOptionLogoutClick()
    fun loadUserFromDatabase()

}