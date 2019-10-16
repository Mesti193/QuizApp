package com.arturostrowski.quiz.app.ui.leaderboard.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.GetLeaderboardRequest
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class LeaderboardInteractor @Inject constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), LeaderboardMVPInteractor {

    override fun getLeaderboards(request: GetLeaderboardRequest) = apiHelper.getLeaderboards(request)

    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}