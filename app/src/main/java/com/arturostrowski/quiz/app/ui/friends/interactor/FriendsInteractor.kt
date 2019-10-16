package com.arturostrowski.quiz.app.ui.friends.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.ChangeFriendStatusRequest
import com.arturostrowski.quiz.app.data.network.request.GetFriendsRequest
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class FriendsInteractor @Inject internal constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), FriendsMVPInteractor {

    override fun addFriend(request: AddFriendRequest) = apiHelper.addFriend(request)
    override fun getFriends(request: GetFriendsRequest) = apiHelper.getFriends(request)
    override fun changeFriendStatus(request: ChangeFriendStatusRequest) = apiHelper.changeFriendStatus(request)
    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}