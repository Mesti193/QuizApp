package com.arturostrowski.quiz.app.ui.notifications.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.GetNotificationsRequest
import com.arturostrowski.quiz.app.data.network.request.SetNotificationAsReadRequest
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class NotificationsInteractor @Inject internal constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), NotificationsMVPInteractor {

    override fun getNotifications(request: GetNotificationsRequest) = apiHelper.getUserNotifications(request)

    override fun setNotificationAsRead(request: SetNotificationAsReadRequest) = apiHelper.setUserNotificationAsRead(request)

    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}