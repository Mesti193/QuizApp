package com.arturostrowski.quiz.app.ui.login

import com.arturostrowski.quiz.app.ui.login.interactor.LoginInteractor
import com.arturostrowski.quiz.app.ui.login.interactor.LoginMVPInteractor
import com.arturostrowski.quiz.app.ui.login.presenter.LoginMVPPresenter
import com.arturostrowski.quiz.app.ui.login.presenter.LoginPresenter
import com.arturostrowski.quiz.app.ui.login.view.LoginFragment
import com.arturostrowski.quiz.app.ui.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

@Module
class LoginFragmentModule {

    @Provides
    internal fun provideLoginInteractor(interactor: LoginInteractor): LoginMVPInteractor = interactor

    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenter<LoginMVPView, LoginMVPInteractor>)
            : LoginMVPPresenter<LoginMVPView, LoginMVPInteractor> = presenter

    @Provides
    internal fun provideLinearLayoutManager(fragment: LoginFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}