package com.arturostrowski.quiz.app.ui.settings

import com.arturostrowski.quiz.app.ui.settings.view.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class SettingsFragmentProvider {

    @ContributesAndroidInjector(modules = [(SettingsFragmentModule::class)])
    internal abstract fun provideLeaderboardFragmentFactory(): SettingsFragment

}