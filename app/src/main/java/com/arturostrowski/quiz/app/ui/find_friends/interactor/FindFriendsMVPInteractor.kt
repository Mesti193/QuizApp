package com.arturostrowski.quiz.app.ui.find_friends.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddFriendRequest
import com.arturostrowski.quiz.app.data.network.request.SearchFriendsRequest
import com.arturostrowski.quiz.app.data.network.response.AddFriendResponse
import com.arturostrowski.quiz.app.data.network.response.SearchFriendsResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface FindFriendsMVPInteractor : MVPInteractor {

    fun addFriend(request: AddFriendRequest): Observable<AddFriendResponse>
    fun searchFriends(request: SearchFriendsRequest): Observable<SearchFriendsResponse>
    fun loadUser(): Observable<List<User>>

}