package com.arturostrowski.quiz.app.ui.find_friends.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.SearchFriends
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.find_friends.interactor.FindFriendsMVPInteractor
import com.arturostrowski.quiz.app.ui.find_friends.presenter.FindFriendsMVPPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.fragment_find_friends.*
import javax.inject.Inject


class FindFriendsFragment : BaseFragment(), FindFriendsMVPView {
    companion object {
        val TAG = FindFriendsFragment::class.java.simpleName
    }

    fun newInstance(): FindFriendsFragment {
        val findFriendsFragment = FindFriendsFragment()
        return findFriendsFragment
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: FindFriendsMVPPresenter<FindFriendsMVPView, FindFriendsMVPInteractor>

    private var user: User? = null
    private lateinit var findFriendsAdapter: FindFriendsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_find_friends, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.find_friends_toolbar_title))
        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)

        findFriendsAdapter = FindFriendsAdapter( context!!, mutableListOf(), {

        }, {
            presenter.onAddFriend(user, it.userID)
        }, {

        })

        rvFriends.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = findFriendsAdapter
        }

        searchView.queryHint = "Search View"
        searchView.onActionViewExpanded()
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                query.isNotEmpty().let { presenter.onSearchFriendPrepared(user, query) }.run { findFriendsAdapter.clearList() }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText.isNotEmpty().let { presenter.onSearchFriendPrepared(user, newText) }.run { findFriendsAdapter.clearList() }
                return false
            }
        })
    }

    override fun removeMoreMenu() {

    }

    override fun displayFriends(friends: ArrayList<SearchFriends>) {
        if(friends.size > 0){
            findFriendsAdapter.addList(friends)
        }else{
            findFriendsAdapter.clearList()
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