package com.arturostrowski.quiz.app.ui.find_friends

import com.arturostrowski.quiz.app.ui.find_friends.interactor.FindFriendsInteractor
import com.arturostrowski.quiz.app.ui.find_friends.interactor.FindFriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.find_friends.presenter.FindFriendsMVPPresenter
import com.arturostrowski.quiz.app.ui.find_friends.presenter.FindFriendsPresenter
import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsFragment
import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsMVPView
import dagger.Module
import dagger.Provides

@Module
class FindFriendsFragmentModule {

    @Provides
    internal fun provideFriendsInteractor(interactor: FindFriendsInteractor): FindFriendsMVPInteractor = interactor

    @Provides
    internal fun provideFriendsPresenter(presenter: FindFriendsPresenter<FindFriendsMVPView, FindFriendsMVPInteractor>)
            : FindFriendsMVPPresenter<FindFriendsMVPView, FindFriendsMVPInteractor> = presenter


    @Provides
    internal fun provideLinearLayoutManager(fragment: FindFriendsFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}