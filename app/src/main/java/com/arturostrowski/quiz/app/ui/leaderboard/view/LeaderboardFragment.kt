package com.arturostrowski.quiz.app.ui.leaderboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.GlobalLeaderboard
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.leaderboard.interactor.LeaderboardMVPInteractor
import com.arturostrowski.quiz.app.ui.leaderboard.presenter.LeaderboardMVPPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import javax.inject.Inject

class LeaderboardFragment : BaseFragment(), LeaderboardMVPView {
    companion object {
        val TAG = LeaderboardFragment::class.java.simpleName
        val FRIENDS_LEADERBOARD = 1
        val GLOBAL_LEADERBOARD = 2
    }

    fun newInstance(): LeaderboardFragment {
        val leaderboardFragment = LeaderboardFragment()
        return leaderboardFragment
    }

    @Inject
    internal lateinit var layoutManager: androidx.recyclerview.widget.LinearLayoutManager
    @Inject
    internal lateinit var presenter: LeaderboardMVPPresenter<LeaderboardMVPView, LeaderboardMVPInteractor>

    private var user: User? = null
    private var globalLeaderboard: ArrayList<GlobalLeaderboard>? = null
    private var friendsLeaderboard: ArrayList<GlobalLeaderboard>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_leaderboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.empty))
        presenter.loadUserFromDatabase()
    }

    private fun changeLeaderboard(buttonClicked: Int){
        when(buttonClicked){
            FRIENDS_LEADERBOARD -> {
                displayLeaderboard(friendsLeaderboard!!)
                btFriends.setTextColor(context!!.getColor(R.color.white))
                btFriends.setBackgroundColor(context!!.getColor(R.color.leaderboard_button_active))
                btGlobal.setTextColor(context!!.getColor(R.color.text_grey2))
                btGlobal.setBackgroundColor(context!!.getColor(R.color.leaderboard_button))
            }
            GLOBAL_LEADERBOARD -> {
                displayLeaderboard(globalLeaderboard!!)
                btGlobal.setTextColor(context!!.getColor(R.color.white))
                btGlobal.setBackgroundColor(context!!.getColor(R.color.leaderboard_button_active))
                btFriends.setTextColor(context!!.getColor(R.color.text_grey2))
                btFriends.setBackgroundColor(context!!.getColor(R.color.leaderboard_button))
            }
        }
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        presenter.onLeaderboardPrepared(user)
    }

    override fun displayGlobalLeaderboard(globalLeaderboard: ArrayList<GlobalLeaderboard>) {
        this.globalLeaderboard = globalLeaderboard
        btGlobal.setOnClickListener {
            changeLeaderboard(GLOBAL_LEADERBOARD)
        }
    }

    override fun displayFriendsLeaderboard(friendsLeaderboard: ArrayList<GlobalLeaderboard>) {
        this.friendsLeaderboard = friendsLeaderboard
        changeLeaderboard(FRIENDS_LEADERBOARD)
        btFriends.setOnClickListener {
            changeLeaderboard(FRIENDS_LEADERBOARD)
        }
    }

    private fun displayLeaderboard(leaderboard: ArrayList<GlobalLeaderboard>){
        var tmpLeaderboard: ArrayList<GlobalLeaderboard> = arrayListOf()
        tmpLeaderboard.addAll(leaderboard)
        if(tmpLeaderboard[0].picture.isEmpty()){
            ivProfile.setImageDrawable(context!!.getDrawable(R.drawable.ic_user))
        }else{
            Picasso
                    .get()
                    .load(tmpLeaderboard[0].picture)
                    .into(ivProfile)
        }
        tvName.text = "${tmpLeaderboard[0].firstName} ${tmpLeaderboard[0].lastName}"
        tvPoints.text = "Points: ${tmpLeaderboard[0].points}"
        tmpLeaderboard.removeAt(0)
        rvLeaderboard.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = LeaderboardAdapter(tmpLeaderboard)
        }
    }

    override fun displayError(error: String) {

    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}