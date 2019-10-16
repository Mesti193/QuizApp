package com.arturostrowski.quiz.app.ui.find_friends.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.SearchFriendsRequest
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.find_friends.interactor.FindFriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class FindFriendsPresenter<V : FindFriendsMVPView, I : FindFriendsMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), FindFriendsMVPPresenter<V, I> {

    override fun onAddFriend(user: User, friendID: Long) {
        interactor?.let {
            it.addFriend(AddFriendRequest(user.userID, friendID, user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ friendsResponse ->
                        getView()?.let {view ->
                            if(friendsResponse.success){
//                                view.reloadFriends()
                                view.removeMoreMenu()
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

    override fun onSearchFriendPrepared(user: User, query: String) {
        interactor?.let {
            it.searchFriends(SearchFriendsRequest(user.userID, user.securityToken, query))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ searchFriendResponse ->
                        getView()?.let {view ->
                            if(searchFriendResponse.success){
                                searchFriendResponse.friends?.let { view.displayFriends(it) }
                            }else{
                                view.displayFriends(arrayListOf())
                                view.displayError(searchFriendResponse.error!!.message)
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