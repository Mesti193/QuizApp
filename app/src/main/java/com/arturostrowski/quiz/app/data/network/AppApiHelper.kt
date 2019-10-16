package com.arturostrowski.quiz.app.data.network

import com.arturostrowski.quiz.app.data.network.request.*
import com.arturostrowski.quiz.app.data.network.response.*
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    override fun login(request: LoginRequest): Observable<LoginResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(LoginResponse::class.java)

    override fun getLeaderboards(request: GetLeaderboardRequest): Observable<GetLeaderboardResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LEADERBOARD)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(GetLeaderboardResponse::class.java)

    override fun getUserNotifications(request: GetNotificationsRequest): Observable<GetNotificationsResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_NOTIFICATIONS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(GetNotificationsResponse::class.java)

    override fun setUserNotificationAsRead(request: SetNotificationAsReadRequest): Observable<SetNotificationAsReadResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SET_NOTIFICATION_AS_READ)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(SetNotificationAsReadResponse::class.java)

    override fun getFriends(request: GetFriendsRequest): Observable<GetFriendsResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FRIENDS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(GetFriendsResponse::class.java)

    override fun addFriend(request: AddFriendRequest): Observable<AddFriendResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_ADD_FRIENDS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(AddFriendResponse::class.java)

    override fun changeFriendStatus(request: ChangeFriendStatusRequest): Observable<ChangeFriendStatusResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CHANGE_FRIEND_STATUS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(ChangeFriendStatusResponse::class.java)

    override fun searchFriends(request: SearchFriendsRequest): Observable<SearchFriendsResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SEARCH_FRIENDS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(SearchFriendsResponse::class.java)

    override fun getChats(request: GetChatRequest): Observable<GetChatResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CHATS)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(GetChatResponse::class.java)

    override fun getChatContent(request: GetChatContentRequest): Observable<GetChatContentResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CHAT_CONTENT)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(GetChatContentResponse::class.java)


    override fun addChatContent(request: AddChatContentRequest): Observable<AddChatContentResponse> =
            Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_ADD_CHAT_CONTENT)
                    .addHeaders(apiHeader.quizApiHeader)
                    .addApplicationJsonBody(request)
                    .build()
                    .getObjectObservable(AddChatContentResponse::class.java)

}