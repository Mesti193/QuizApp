package com.arturostrowski.quiz.app.ui.splash.presenter

import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.splash.interactor.SplashMVPInteractor
import com.arturostrowski.quiz.app.ui.splash.view.SplashMVPView

interface SplashMVPPresenter<V : SplashMVPView, I : SplashMVPInteractor> : MVPPresenter<V,I>