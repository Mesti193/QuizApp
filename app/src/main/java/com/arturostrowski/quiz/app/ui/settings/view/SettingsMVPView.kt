package com.arturostrowski.quiz.app.ui.settings.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface SettingsMVPView : MVPView {

    fun getUser(user: User)
    fun displayError(error: String)
    fun logout()

}