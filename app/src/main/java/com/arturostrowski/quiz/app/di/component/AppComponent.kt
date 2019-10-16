package com.arturostrowski.quiz.app.di.component

import android.app.Application
import com.arturostrowski.quiz.app.MvpApp
import com.arturostrowski.quiz.app.di.builder.ActivityBuilder
import com.arturostrowski.quiz.app.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}