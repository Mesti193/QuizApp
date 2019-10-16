package com.arturostrowski.quiz.app.ui.login.presenter

import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.di.md5
import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.login.interactor.LoginMVPInteractor
import com.arturostrowski.quiz.app.ui.login.view.LoginMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter<V : LoginMVPView, I : LoginMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), LoginMVPPresenter<V, I> {

    override fun onServerLoginClicked(email: String, password: String) {
        when {
            email.isEmpty() -> getView()?.showValidationMessage(R.string.invalid_email_error_message)
            password.isEmpty() -> getView()?.showValidationMessage(R.string.empty_password_error_message)
            else -> {
//                getView()?.showProgress()
                interactor?.let {
                    it.doServerLoginApiCall(email, password.md5())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ loginResponse ->
                                getView()?.let {
                                    if(loginResponse.success){
                                        inserUserToDatabase(loginResponse.user)
                                    }else{
                                        it.displayError(loginResponse.error!!.message)
                                    }
//                                    it.hideProgress()
                                }
                            }, { throwable ->
                                getView()?.let {
                                    it.displayError(throwable.message.toString())
//                                    it.hideProgress()
                                }
                            })
                }

            }
        }
    }

    override fun inserUserToDatabase(user: User) {
        Thread {
            interactor?.let {
                compositeDisposable.add(it.insertUser(user)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            getView()?.let {
                                it.openMainFragment()
                            }
                        })
            }
        }.start()
    }

}