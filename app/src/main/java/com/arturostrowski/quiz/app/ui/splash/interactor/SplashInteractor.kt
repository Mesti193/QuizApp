package com.arturostrowski.quiz.app.ui.splash.interactor

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import com.arturostrowski.quiz.app.util.AppConstants
import com.arturostrowski.quiz.app.util.FileUtils
import io.reactivex.Observable
import javax.inject.Inject

class SplashInteractor @Inject constructor(private val mContext: Context, private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), SplashMVPInteractor {

    override fun isUserLogged(): Observable<Boolean> {
        return userRepo.isUserRepoEmpty().concatMap { isEmpty ->
            if(isEmpty){
                Observable.just(false)
            } else {
                Observable.just(true)
            }
        }

    }

}