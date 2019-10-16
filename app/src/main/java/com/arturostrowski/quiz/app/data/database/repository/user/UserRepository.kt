package com.arturostrowski.quiz.app.data.database.repository.user

import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject internal constructor(private val userDao: UserDao): UserRepo {

    override fun isUserRepoEmpty(): Observable<Boolean> = Observable.fromCallable { userDao.loadUser().isEmpty() }

    override fun insertUser(user: User): Observable<Boolean> {
        userDao.insert(user)
        return Observable.just(true)
    }

    override fun loadUser(): Observable<List<User>> = Observable.fromCallable { userDao.loadUser() }

    override fun logout(): Observable<Boolean> {
        userDao.logout()
        return Observable.just(true)
    }

}