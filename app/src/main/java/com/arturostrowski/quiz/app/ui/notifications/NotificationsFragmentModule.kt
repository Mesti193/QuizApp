package com.arturostrowski.quiz.app.ui.notifications

import com.arturostrowski.quiz.app.ui.notifications.interactor.NotificationsInteractor
import com.arturostrowski.quiz.app.ui.notifications.interactor.NotificationsMVPInteractor
import com.arturostrowski.quiz.app.ui.notifications.presenter.NotificationsMVPPresenter
import com.arturostrowski.quiz.app.ui.notifications.presenter.NotificationsPresenter
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsFragment
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsMVPView
import dagger.Module
import dagger.Provides

@Module
class NotificationsFragmentModule {

    @Provides
    internal fun provideNotificationsInteractor(interactor: NotificationsInteractor): NotificationsMVPInteractor = interactor

    @Provides
    internal fun provideNotificationsPresenter(presenter: NotificationsPresenter<NotificationsMVPView, NotificationsMVPInteractor>)
            : NotificationsMVPPresenter<NotificationsMVPView, NotificationsMVPInteractor> = presenter


    @Provides
    internal fun provideLinearLayoutManager(fragment: NotificationsFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}