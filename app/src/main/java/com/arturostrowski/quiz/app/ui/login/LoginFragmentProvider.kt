package com.arturostrowski.quiz.app.ui.login

import com.arturostrowski.quiz.app.ui.login.view.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class LoginFragmentProvider {

    @ContributesAndroidInjector(modules = [(LoginFragmentModule::class)])
    internal abstract fun provideLoginFragmentFactory(): LoginFragment

}