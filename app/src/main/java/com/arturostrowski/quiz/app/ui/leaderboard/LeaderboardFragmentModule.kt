package com.arturostrowski.quiz.app.ui.leaderboard

import com.arturostrowski.quiz.app.ui.leaderboard.interactor.LeaderboardInteractor
import com.arturostrowski.quiz.app.ui.leaderboard.interactor.LeaderboardMVPInteractor
import com.arturostrowski.quiz.app.ui.leaderboard.presenter.LeaderboardMVPPresenter
import com.arturostrowski.quiz.app.ui.leaderboard.presenter.LeaderboardPresenter
import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardFragment
import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardMVPView
import dagger.Module
import dagger.Provides

@Module
class LeaderboardFragmentModule {

    @Provides
    internal fun provideLeaderboardInteractor(interactor: LeaderboardInteractor): LeaderboardMVPInteractor = interactor

    @Provides
    internal fun provideLeaderboardPresenter(presenter: LeaderboardPresenter<LeaderboardMVPView, LeaderboardMVPInteractor>)
            : LeaderboardMVPPresenter<LeaderboardMVPView, LeaderboardMVPInteractor> = presenter

    @Provides
    internal fun provideLinearLayoutManager(fragment: LeaderboardFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}