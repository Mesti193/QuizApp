package com.arturostrowski.quiz.app.ui.main

import com.arturostrowski.quiz.app.ui.main.interactor.MainInteractor
import com.arturostrowski.quiz.app.ui.main.interactor.MainMVPInteractor
import com.arturostrowski.quiz.app.ui.main.presenter.MainMVPPresenter
import com.arturostrowski.quiz.app.ui.main.presenter.MainPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}