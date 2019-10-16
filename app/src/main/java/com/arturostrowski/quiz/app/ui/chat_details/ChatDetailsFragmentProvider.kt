package com.arturostrowski.quiz.app.ui.chat_details

import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ChatDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = [(ChatDetailsFragmentModule::class)])
    internal abstract fun provideChatDetailsFragmentFactory(): ChatDetailsFragment

}