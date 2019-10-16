package com.arturostrowski.quiz.app.ui.notifications.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetNotificationsRequest
import com.arturostrowski.quiz.app.data.network.request.SetNotificationAsReadRequest
import com.arturostrowski.quiz.app.data.network.response.GetNotificationsResponse
import com.arturostrowski.quiz.app.data.network.response.SetNotificationAsReadResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface NotificationsMVPInteractor : MVPInteractor {

    fun getNotifications(request: GetNotificationsRequest): Observable<GetNotificationsResponse>
    fun setNotificationAsRead(request: SetNotificationAsReadRequest): Observable<SetNotificationAsReadResponse>
    fun loadUser(): Observable<List<User>>

}