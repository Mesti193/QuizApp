package com.arturostrowski.quiz.app.ui.leaderboard.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetLeaderboardRequest
import com.arturostrowski.quiz.app.data.network.response.GetLeaderboardResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface LeaderboardMVPInteractor : MVPInteractor {

    fun getLeaderboards(request: GetLeaderboardRequest): Observable<GetLeaderboardResponse>
    fun loadUser(): Observable<List<User>>

}