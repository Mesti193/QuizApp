package com.arturostrowski.quiz.app.ui.notifications

import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class NotificationsFragmentProvider {

    @ContributesAndroidInjector(modules = [(NotificationsFragmentModule::class)])
    internal abstract fun provideNotificationsFragmentFactory(): NotificationsFragment

}