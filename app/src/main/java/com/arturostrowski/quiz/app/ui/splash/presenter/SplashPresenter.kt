package com.arturostrowski.quiz.app.ui.splash.presenter

import com.arturostrowski.quiz.app.ui.base.presenter.BasePresenter
import com.arturostrowski.quiz.app.ui.splash.interactor.SplashMVPInteractor
import com.arturostrowski.quiz.app.ui.splash.view.SplashMVPView
import com.arturostrowski.quiz.app.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashMVPView, I : SplashMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), SplashMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        feedInDatabase()
    }

    private fun feedInDatabase() = interactor?.let {
        compositeDisposable.add(it.isUserLogged()
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe {isLogged ->
                    getView()?.let {
                        if(isLogged){
                            it.openMainActivity()
                        }else{
                            it.openLoginActivity()
                        }
                    }
                })

    }
    
}