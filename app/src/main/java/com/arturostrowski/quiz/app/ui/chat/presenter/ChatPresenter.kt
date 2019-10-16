package com.arturostrowski.quiz.app.ui.chat.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetChatRequest
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.chat.interactor.ChatMVPInteractor
import com.arturostrowski.quiz.app.ui.chat.view.ChatMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatPresenter<V : ChatMVPView, I : ChatMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), ChatMVPPresenter<V, I> {

    override fun onChatsPrepared(user: User) {
        interactor?.let {
            it.getChats(GetChatRequest(user.userID, user.securityToken))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ chatsResponse ->
                        getView()?.let {view ->
                            if(chatsResponse.success){
                                chatsResponse.chats?.let { view.displayChats(it) }
                            }else{
                                view.displayError(chatsResponse.error!!.message)
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