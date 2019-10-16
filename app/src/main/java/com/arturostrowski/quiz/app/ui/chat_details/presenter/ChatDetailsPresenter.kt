package com.arturostrowski.quiz.app.ui.chat_details.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddChatContentRequest
import com.arturostrowski.quiz.app.data.network.request.GetChatContentRequest
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.chat_details.interactor.ChatDetailsMVPInteractor
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ChatDetailsPresenter<V : ChatDetailsMVPView, I : ChatDetailsMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), ChatDetailsMVPPresenter<V, I> {

    override fun onAddedChatContent(user: User, chat: Chat, message: String, timestamp: Long) {
        interactor?.let {
            it.addChatContent(AddChatContentRequest(user.userID, user.securityToken, chat.chatID, message))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ chatContentResponse ->
                        getView()?.let {view ->
                            if(chatContentResponse.success){
                                onChatContentPrepared(user, chat, timestamp)
                            }else{
                                view.displayError(chatContentResponse.error!!.message)
                            }
                        }
                    }, { throwable ->
                        getView()?.let {
                            it.displayError(throwable.message.toString())
                        }
                    })
        }
    }

    override fun onChatContentPrepared(user: User, chat: Chat, timestamp: Long) {
        interactor?.let {
            it.getChatContent(GetChatContentRequest(user.userID, user.securityToken, chat.chatID, timestamp))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ chatContentResponse ->
                        getView()?.let {view ->
                            if(chatContentResponse.success){
                                chatContentResponse.chatContent?.let { view.displayChatContent(it, chatContentResponse.timestamp) }
                                chatContentResponse.user?.let { view.displayUser(it) }
                            }else{
                                view.displayError(chatContentResponse.error!!.message)
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