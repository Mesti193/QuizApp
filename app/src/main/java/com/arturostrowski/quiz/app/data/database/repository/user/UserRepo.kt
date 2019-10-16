package com.arturostrowski.quiz.app.data.database.repository.user

import io.reactivex.Observable

interface UserRepo {

    fun isUserRepoEmpty(): Observable<Boolean>

    fun insertUser(user: User): Observable<Boolean>

    fun loadUser(): Observable<List<User>>

    fun logout(): Observable<Boolean>

}