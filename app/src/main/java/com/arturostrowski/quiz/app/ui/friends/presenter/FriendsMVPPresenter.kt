package com.arturostrowski.quiz.app.ui.friends.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.friends.interactor.FriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.friends.view.FriendsMVPView

interface FriendsMVPPresenter<V : FriendsMVPView, I : FriendsMVPInteractor> : MVPPresenter<V, I> {

    fun onAddFriend(user: User, friendID: Long)
    fun onFriendsPrepared(user: User)
    fun onFriendStatusChanged(user: User, friendID: Long, typeID: Long)
    fun loadUserFromDatabase()

}