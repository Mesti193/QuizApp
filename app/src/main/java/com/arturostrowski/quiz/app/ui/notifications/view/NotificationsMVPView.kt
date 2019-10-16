package com.arturostrowski.quiz.app.ui.notifications.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Notifications
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface NotificationsMVPView : MVPView {

    fun getUser(user: User)
    fun displayNotifications(notifications: ArrayList<Notifications>)
    fun displayError(error: String)

}