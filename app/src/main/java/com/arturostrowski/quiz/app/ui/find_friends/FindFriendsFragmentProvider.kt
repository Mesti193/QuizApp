package com.arturostrowski.quiz.app.ui.find_friends

import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FindFriendsFragmentProvider {

    @ContributesAndroidInjector(modules = [(FindFriendsFragmentModule::class)])
    internal abstract fun provideFindFriendsFragmentFactory(): FindFriendsFragment

}