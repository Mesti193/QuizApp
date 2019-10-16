package com.arturostrowski.quiz.app.ui.main.presenter

import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.main.interactor.MainMVPInteractor
import com.arturostrowski.quiz.app.ui.main.view.MainMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), MainMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
//        getUserData()
//        getQuestionCards()
        loadUserFromDatabase()
    }

    override fun onDrawerOptionChatClick() = getView()?.openChatFragment()

    override fun onDrawerOptionFindFriendsClick() = getView()?.openFindFriendsFragment()

    override fun onDrawerOptionFriendsClick() = getView()?.openFriendsFragment()

    override fun onDrawerOptionNotificationsClick() = getView()?.openNotificationsFragment()

    override fun onDrawerOptionLeaderboardClick() = getView()?.openLeaderboardFragment()

    override fun onDrawerOptionSettingsClick() = getView()?.openSettingsFragment()

    override fun onDrawerOptionLogoutClick() {
        Thread {
            interactor?.let {
                compositeDisposable.add(it.logout()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            getView()?.let {
                                it.logout()
                            }
                        })
            }
        }.start()
    }

    private fun getUserData() = interactor?.let {
        val userData = it.getUserDetails()
        getView()?.inflateUserDetails(userData)
    }


    override fun loadUserFromDatabase() {
        interactor?.let {
            compositeDisposable.add(it.loadUser()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe {userList ->
                        getView()?.let {
                            if(userList != null && userList.isNotEmpty()){
                                it.setUser(userList[0])
                            }
                        }
                    })
        }
    }

}