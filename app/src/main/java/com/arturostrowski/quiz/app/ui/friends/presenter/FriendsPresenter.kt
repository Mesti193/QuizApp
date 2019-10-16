package com.arturostrowski.quiz.app.ui.friends.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.ChangeFriendStatusRequest
import com.arturostrowski.quiz.app.data.network.request.GetFriendsRequest
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.friends.interactor.FriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.friends.view.FriendsMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FriendsPresenter<V : FriendsMVPView, I : FriendsMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), FriendsMVPPresenter<V, I> {

    override fun onAddFriend(user: User, friendID: Long) {
        interactor?.let {
            it.addFriend(AddFriendRequest(user.userID, friendID, user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ friendsResponse ->
                        getView()?.let {view ->
                            if(friendsResponse.success){
                                view.reloadFriends()
                            }else{
                                view.displayError(friendsResponse.error!!.message)
                            }
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
                        }
                    })
        }
    }

    override fun onFriendsPrepared(user: User) {
        interactor?.let {
            it.getFriends(GetFriendsRequest(userID = user.userID, securityToken = user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ friendsResponse ->
                        getView()?.let {view ->
                            if(friendsResponse.success){
                                friendsResponse.friends?.let { view.displayFriends(it) }
                                friendsResponse.friendRequests?.let { view.displayFriendRequests(it) }
                            }else{
                                view.displayFriends(arrayListOf())
                                view.displayFriendRequests(arrayListOf())
                                view.displayError(friendsResponse.error!!.message)
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

    override fun onFriendStatusChanged(user: User, friendID: Long, typeID: Long) {
        interactor?.let {
            it.changeFriendStatus(ChangeFriendStatusRequest(user.userID, friendID, typeID, user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        getView()?.let {view ->
                            if(response.success){
                                view.reloadFriends()
                            }else{
                                view.displayError(response.error!!.message)
                            }
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
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