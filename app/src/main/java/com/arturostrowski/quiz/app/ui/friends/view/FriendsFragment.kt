package com.arturostrowski.quiz.app.ui.friends.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Friend
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.friends.interactor.FriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.friends.presenter.FriendsMVPPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.fragment_friends.*
import javax.inject.Inject

class FriendsFragment : BaseFragment(), FriendsMVPView {
    companion object {
        val TAG = FriendsFragment::class.java.simpleName
        val CONFIRM_FRIEND = 1
        val DELETE_FRIEND = 2
    }

    fun newInstance(): FriendsFragment {
        val friendsFragment = FriendsFragment()
        return friendsFragment
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: FriendsMVPPresenter<FriendsMVPView, FriendsMVPInteractor>

    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_friends, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        swipeRefresh.setOnRefreshListener {
            if(user != null){
                presenter.onFriendsPrepared(user!!) // refresh your list contents somehow
                swipeRefresh.isRefreshing = false   // reset the SwipeRefreshLayout (stop the loading spinner)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.friends_toolbar_title))
        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        presenter.onFriendsPrepared(user)
    }

    override fun reloadFriends() {
        user?.let { presenter.onFriendsPrepared(it) }
    }

    override fun displayFriends(friends: ArrayList<Friend>) {
        if(friends.size>0){
            tvFriends.visibility = View.VISIBLE
        }else{
            tvFriends.visibility = View.GONE
        }
        rvFriends.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FriendsAdapter( context, friends, {

            }, {
                user?.let { user ->
                    presenter.onFriendStatusChanged(user, it.userID, DELETE_FRIEND.toLong())
                }
            })
        }
    }

    override fun displayFriendRequests(friendRequests: ArrayList<Friend>) {
        if(friendRequests.size>0){
            tvFriendRequests.visibility = View.VISIBLE
        }else{
            tvFriendRequests.visibility = View.GONE
        }
        rvFriendRequests.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FriendsRequestsAdapter( friendRequests, {
                user?.let { user ->
                    presenter.onFriendStatusChanged(user, it.userID, CONFIRM_FRIEND.toLong())
                }
            }, {
                user?.let { user ->
                    presenter.onFriendStatusChanged(user, it.userID, DELETE_FRIEND.toLong())
                }
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