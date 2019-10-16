package com.arturostrowski.quiz.app.ui.leaderboard

import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class LeaderboardFragmentProvider {

    @ContributesAndroidInjector(modules = [(LeaderboardFragmentModule::class)])
    internal abstract fun provideLeaderboardFragmentFactory(): LeaderboardFragment

}