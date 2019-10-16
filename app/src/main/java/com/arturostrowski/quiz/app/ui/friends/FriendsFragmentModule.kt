package com.arturostrowski.quiz.app.ui.friends

import com.arturostrowski.quiz.app.ui.friends.interactor.FriendsInteractor
import com.arturostrowski.quiz.app.ui.friends.interactor.FriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.friends.presenter.FriendsMVPPresenter
import com.arturostrowski.quiz.app.ui.friends.presenter.FriendsPresenter
import com.arturostrowski.quiz.app.ui.friends.view.FriendsFragment
import com.arturostrowski.quiz.app.ui.friends.view.FriendsMVPView
import dagger.Module
import dagger.Provides

@Module
class FriendsFragmentModule {

    @Provides
    internal fun provideFriendsInteractor(interactor: FriendsInteractor): FriendsMVPInteractor = interactor

    @Provides
    internal fun provideFriendsPresenter(presenter: FriendsPresenter<FriendsMVPView, FriendsMVPInteractor>)
            : FriendsMVPPresenter<FriendsMVPView, FriendsMVPInteractor> = presenter


    @Provides
    internal fun provideLinearLayoutManager(fragment: FriendsFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}