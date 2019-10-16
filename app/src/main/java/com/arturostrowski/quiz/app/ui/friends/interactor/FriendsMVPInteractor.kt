package com.arturostrowski.quiz.app.ui.friends.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.ChangeFriendStatusRequest
import com.arturostrowski.quiz.app.data.network.request.GetFriendsRequest
import com.arturostrowski.quiz.app.data.network.response.AddFriendResponse
import com.arturostrowski.quiz.app.data.network.response.ChangeFriendStatusResponse
import com.arturostrowski.quiz.app.data.network.response.GetFriendsResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable


interface FriendsMVPInteractor : MVPInteractor {

    fun addFriend(request: AddFriendRequest): Observable<AddFriendResponse>
    fun getFriends(request: GetFriendsRequest): Observable<GetFriendsResponse>
    fun changeFriendStatus(request: ChangeFriendStatusRequest): Observable<ChangeFriendStatusResponse>
    fun loadUser(): Observable<List<User>>

}