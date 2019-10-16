package com.arturostrowski.quiz.app.ui.notifications.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetNotificationsRequest
import com.arturostrowski.quiz.app.data.network.request.SetNotificationAsReadRequest
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.notifications.interactor.NotificationsMVPInteractor
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotificationsPresenter<V : NotificationsMVPView, I : NotificationsMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), NotificationsMVPPresenter<V, I> {

    override fun onNotificationsPrepared(user: User) {
//        getView()?.showProgress()
        interactor?.let {
            it.getNotifications(GetNotificationsRequest(userID = user.userID, securityToken = user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ notificationsResponse ->
                        getView()?.let {
                            if(notificationsResponse.success){
                                it.displayNotifications(notificationsResponse.notifications!!)
                            }else{
                                it.displayNotifications(arrayListOf())
                                it.displayError(notificationsResponse.error!!.message)
                            }
//                            it.hideProgress()
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
                            it.displayNotifications(arrayListOf())
//                            it.hideProgress()
                        }
                    })
        }
    }

    override fun onNotificationRead(id: Long, user: User) {
//        getView()?.showProgress()
        interactor?.let {
            it.setNotificationAsRead(SetNotificationAsReadRequest(userID = user.userID, userNotificationsID = id, securityToken = user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ notificationsResponse ->
                        getView()?.let {
                            if(notificationsResponse.success){
//                                it.displayNotifications(notificationsResponse.notifications!!)
                            }else{
//                                it.displayNotifications(arrayListOf())
                                it.displayError(notificationsResponse.error!!.message)
                            }
//                            it.hideProgress()
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
//                            it.displayNotifications(arrayListOf())
//                            it.hideProgress()
                        }
                    })
        }
    }

    override fun loadUserFromDatabase() {
        interactor?.let {
            compositeDisposable.add(it.loadUser()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe {userList ->
                        getView()?.let {
                            it.getUser(userList[0])
                        }
                    })
        }
    }

}