package com.arturostrowski.quiz.app.ui.find_friends.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.SearchFriendsRequest
import com.arturostrowski.quiz.app.data.network.response.SearchFriendsResponse
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class FindFriendsInteractor @Inject internal constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), FindFriendsMVPInteractor {

    override fun addFriend(request: AddFriendRequest) = apiHelper.addFriend(request)
    override fun searchFriends(request: SearchFriendsRequest): Observable<SearchFriendsResponse> = apiHelper.searchFriends(request)
    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}