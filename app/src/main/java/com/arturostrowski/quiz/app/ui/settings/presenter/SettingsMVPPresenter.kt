package com.arturostrowski.quiz.app.ui.settings.presenter

import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.settings.interactor.SettingsMVPInteractor
import com.arturostrowski.quiz.app.ui.settings.view.SettingsMVPView

interface SettingsMVPPresenter<V : SettingsMVPView, I : SettingsMVPInteractor> : MVPPresenter<V, I> {

    fun loadUserFromDatabase()
    fun onLogoutButtonClick()

}