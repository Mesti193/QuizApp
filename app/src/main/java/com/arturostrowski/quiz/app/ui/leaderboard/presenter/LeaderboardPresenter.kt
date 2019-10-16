package com.arturostrowski.quiz.app.ui.leaderboard.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetLeaderboardRequest
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.leaderboard.interactor.LeaderboardMVPInteractor
import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LeaderboardPresenter<V : LeaderboardMVPView, I : LeaderboardMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), LeaderboardMVPPresenter<V, I> {

    override fun onLeaderboardPrepared(user: User)  {
//        getView()?.showProgress()
        interactor?.let {
            it.getLeaderboards(GetLeaderboardRequest(userID = user.userID, securityToken = user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ notificationsResponse ->
                        getView()?.let {view ->
                            if(notificationsResponse.success){
                                notificationsResponse.friendsLeaderboard?.let { view.displayFriendsLeaderboard(it) }
                                notificationsResponse.globalLeaderboard?.let { view.displayGlobalLeaderboard(it) }
                            }else{
                                view.displayError(notificationsResponse.error!!.message)
                            }
//                            view.hideProgress()
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
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