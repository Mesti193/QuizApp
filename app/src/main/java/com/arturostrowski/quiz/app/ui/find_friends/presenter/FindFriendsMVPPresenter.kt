package com.arturostrowski.quiz.app.ui.find_friends.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.find_friends.interactor.FindFriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsMVPView

interface FindFriendsMVPPresenter<V : FindFriendsMVPView, I : FindFriendsMVPInteractor> : MVPPresenter<V, I> {

    fun onAddFriend(user: User, friendID: Long)
    fun onSearchFriendPrepared(user: User, query: String)
    fun loadUserFromDatabase()

}