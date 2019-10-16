package com.arturostrowski.quiz.app.ui.notifications.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.notifications.interactor.NotificationsMVPInteractor
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsMVPView

interface NotificationsMVPPresenter<V : NotificationsMVPView, I : NotificationsMVPInteractor> : MVPPresenter<V, I> {

    fun onNotificationsPrepared(user: User)
    fun onNotificationRead(id: Long, user: User)
    fun loadUserFromDatabase()

}