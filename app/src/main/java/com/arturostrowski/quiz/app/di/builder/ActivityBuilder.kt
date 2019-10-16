package com.arturostrowski.quiz.app.di.builder

import com.arturostrowski.quiz.app.ui.chat.ChatFragmentProvider
import com.arturostrowski.quiz.app.ui.chat_details.ChatDetailsFragmentProvider
import com.arturostrowski.quiz.app.ui.find_friends.FindFriendsFragmentProvider
import com.arturostrowski.quiz.app.ui.friends.FriendsFragmentProvider
import com.arturostrowski.quiz.app.ui.leaderboard.LeaderboardFragmentProvider
import com.arturostrowski.quiz.app.ui.login.LoginFragmentProvider
import com.arturostrowski.quiz.app.ui.main.MainActivityModule
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.ui.notifications.NotificationsFragmentProvider
import com.arturostrowski.quiz.app.ui.profile.ProfileFragmentProvider
import com.arturostrowski.quiz.app.ui.settings.SettingsFragmentProvider
import com.arturostrowski.quiz.app.ui.splash.SplashActivityModule
import com.arturostrowski.quiz.app.ui.splash.view.SplashMVPActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashMVPActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (NotificationsFragmentProvider::class), (LoginFragmentProvider::class),
        (ProfileFragmentProvider::class), (LeaderboardFragmentProvider::class), (SettingsFragmentProvider::class), (FriendsFragmentProvider::class),
        (FindFriendsFragmentProvider::class), (ChatFragmentProvider::class), (ChatDetailsFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity

}