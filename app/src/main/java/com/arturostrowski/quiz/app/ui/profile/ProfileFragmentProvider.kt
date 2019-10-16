package com.arturostrowski.quiz.app.ui.profile

import com.arturostrowski.quiz.app.ui.profile.view.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideProfileFragment(): ProfileFragment

}