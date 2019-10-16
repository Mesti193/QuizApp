package com.arturostrowski.quiz.app.ui.notifications.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Notifications
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.ui.notifications.interactor.NotificationsMVPInteractor
import com.arturostrowski.quiz.app.ui.notifications.presenter.NotificationsMVPPresenter
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.util.*
import javax.inject.Inject

class NotificationsFragment : BaseFragment(), NotificationsMVPView {
    companion object {
        val TAG = NotificationsFragment::class.java.simpleName
    }

    fun newInstance(): NotificationsFragment {
        val notificationsFragment = NotificationsFragment()
        return notificationsFragment
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: NotificationsMVPPresenter<NotificationsMVPView, NotificationsMVPInteractor>

    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_notifications, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        swipeRefresh.setOnRefreshListener {
            if(user != null){
                presenter.onNotificationsPrepared(user!!) // refresh your list contents somehow
                swipeRefresh.isRefreshing = false   // reset the SwipeRefreshLayout (stop the loading spinner)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.notifications_toolbar_title))
        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        presenter.onNotificationsPrepared(user)
    }

    override fun displayNotifications(notifications: ArrayList<Notifications>) {
        var unreadNotifications: Long = 0
        for (notification in notifications) {
            if(!notification.isRead) unreadNotifications++
        }
        tvUnread.text = String.format(Locale.getDefault(), getString(R.string.notifications_unread), unreadNotifications)
        notifications.sortByDescending { it.dateUTC }

        rvNotifications.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NotificationsAdapter( notifications, {
                if(!it.isRead) presenter.onNotificationRead(it.id, user!!)
            }, {
                tvUnread.text = String.format(Locale.getDefault(), getString(R.string.notifications_unread), it)
            })
        }
    }

    override fun displayError(error: String) {
        Log.e(TAG, error)
        Toast.makeText(getBaseActivity(), error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}