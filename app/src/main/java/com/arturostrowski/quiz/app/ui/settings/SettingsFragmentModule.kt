package com.arturostrowski.quiz.app.ui.settings

import com.arturostrowski.quiz.app.ui.settings.interactor.SettingsInteractor
import com.arturostrowski.quiz.app.ui.settings.interactor.SettingsMVPInteractor
import com.arturostrowski.quiz.app.ui.settings.presenter.SettingsMVPPresenter
import com.arturostrowski.quiz.app.ui.settings.presenter.SettingsPresenter
import com.arturostrowski.quiz.app.ui.settings.view.SettingsFragment
import com.arturostrowski.quiz.app.ui.settings.view.SettingsMVPView
import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentModule {

    @Provides
    internal fun provideLeaderboardInteractor(interactor: SettingsInteractor): SettingsMVPInteractor = interactor

    @Provides
    internal fun provideLeaderboardPresenter(presenter: SettingsPresenter<SettingsMVPView, SettingsMVPInteractor>)
            : SettingsMVPPresenter<SettingsMVPView, SettingsMVPInteractor> = presenter

    @Provides
    internal fun provideLinearLayoutManager(fragment: SettingsFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}