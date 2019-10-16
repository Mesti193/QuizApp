package com.arturostrowski.quiz.app.ui.main.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface MainMVPView : MVPView {

    fun setUser(user: User)
    fun inflateUserDetails(userDetails: Pair<String?, String?>)
    fun openLoginActivity()
    fun openFeedActivity()
    fun openAboutFragment()
    fun openChatFragment()
    fun openFindFriendsFragment()
    fun openFriendsFragment()
    fun openNotificationsFragment()
    fun openLeaderboardFragment()
    fun openSettingsFragment()
    fun logout()
//    fun lockDrawer(): Unit?
//    fun unlockDrawer(): Unit?
}