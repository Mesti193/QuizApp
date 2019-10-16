package com.arturostrowski.quiz.app.data.network

import com.arturostrowski.quiz.app.data.network.request.*
import com.arturostrowski.quiz.app.data.network.response.*
import io.reactivex.Observable

interface ApiHelper {

    fun login(request: LoginRequest): Observable<LoginResponse>

    fun getLeaderboards(request: GetLeaderboardRequest): Observable<GetLeaderboardResponse>

    fun getUserNotifications(request: GetNotificationsRequest): Observable<GetNotificationsResponse>

    fun setUserNotificationAsRead(request: SetNotificationAsReadRequest): Observable<SetNotificationAsReadResponse>

    fun getFriends(request: GetFriendsRequest): Observable<GetFriendsResponse>

    fun addFriend(request: AddFriendRequest): Observable<AddFriendResponse>

    fun changeFriendStatus(request: ChangeFriendStatusRequest): Observable<ChangeFriendStatusResponse>

    fun searchFriends(request: SearchFriendsRequest): Observable<SearchFriendsResponse>

    fun getChats(request: GetChatRequest): Observable<GetChatResponse>

    fun getChatContent(request: GetChatContentRequest): Observable<GetChatContentResponse>

    fun addChatContent(request: AddChatContentRequest): Observable<AddChatContentResponse>
}