package com.arturostrowski.quiz.app.ui.login.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.LoginRequest
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), LoginMVPInteractor {

    override fun doServerLoginApiCall(email: String, password: String) =
            apiHelper.login(LoginRequest(email, password))

    override fun insertUser(user: User): Observable<Boolean> {
        userRepo.insertUser(user)
        return Observable.just(true)

//        return userRepo.isUserRepoEmpty().concatMap { isEmpty ->
//            if(isEmpty){
//                userRepo.insertUser(user)
//            } else {
//                Observable.just(false)
//            }
//        }

    }

}