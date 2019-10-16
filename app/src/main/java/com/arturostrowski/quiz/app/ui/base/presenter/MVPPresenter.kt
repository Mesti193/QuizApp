package com.arturostrowski.quiz.app.ui.base.presenter

import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}