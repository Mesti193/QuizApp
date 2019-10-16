package com.arturostrowski.quiz.app.ui.friends

import com.arturostrowski.quiz.app.ui.friends.view.FriendsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FriendsFragmentProvider {

    @ContributesAndroidInjector(modules = [(FriendsFragmentModule::class)])
    internal abstract fun provideFriendsFragmentFactory(): FriendsFragment

}