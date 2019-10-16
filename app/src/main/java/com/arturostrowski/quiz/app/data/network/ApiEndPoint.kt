package com.arturostrowski.quiz.app.data.network

import com.arturostrowski.quiz.app.BuildConfig

object ApiEndPoint {

    const val ENDPOINT_LOGIN = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/user/loginUser"
    const val ENDPOINT_LEADERBOARD = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/leaderboard/getLeaderboard"
    const val ENDPOINT_NOTIFICATIONS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/user/getUserNotifications"
    const val ENDPOINT_FRIENDS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/friends/getFriends"
    const val ENDPOINT_ADD_FRIENDS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/friends/addFriend"
    const val ENDPOINT_CHANGE_FRIEND_STATUS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/friends/changeFriendStatus"
    const val ENDPOINT_SEARCH_FRIENDS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/friends/searchFriends"
    const val ENDPOINT_SET_NOTIFICATION_AS_READ = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/user/setUserNotificationAsRead"
    const val ENDPOINT_CHATS = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/chats/getChats"
    const val ENDPOINT_CHAT_CONTENT = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/chats/getChatContent"
    const val ENDPOINT_ADD_CHAT_CONTENT = com.arturostrowski.quiz.app.BuildConfig.QUIZ_API + "/chats/addChatContent"

}