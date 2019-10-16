package com.arturostrowski.quiz.app.ui.splash

import com.arturostrowski.quiz.app.ui.splash.interactor.SplashInteractor
import com.arturostrowski.quiz.app.ui.splash.interactor.SplashMVPInteractor
import com.arturostrowski.quiz.app.ui.splash.presenter.SplashMVPPresenter
import com.arturostrowski.quiz.app.ui.splash.presenter.SplashPresenter
import com.arturostrowski.quiz.app.ui.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashMVPInteractor = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashMVPView, SplashMVPInteractor>)
            : SplashMVPPresenter<SplashMVPView, SplashMVPInteractor> = splashPresenter
}