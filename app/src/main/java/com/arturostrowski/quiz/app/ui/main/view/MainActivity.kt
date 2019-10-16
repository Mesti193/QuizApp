package com.arturostrowski.quiz.app.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.view.BaseActivity
import com.arturostrowski.quiz.app.ui.chat.view.ChatFragment
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsFragment
import com.arturostrowski.quiz.app.ui.find_friends.view.FindFriendsFragment
import com.arturostrowski.quiz.app.ui.friends.view.FriendsFragment
import com.arturostrowski.quiz.app.ui.leaderboard.view.LeaderboardFragment
import com.arturostrowski.quiz.app.ui.login.view.LoginFragment
import com.arturostrowski.quiz.app.ui.main.interactor.MainMVPInteractor
import com.arturostrowski.quiz.app.ui.main.presenter.MainMVPPresenter
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsFragment
import com.arturostrowski.quiz.app.ui.profile.view.ProfileFragment
import com.arturostrowski.quiz.app.ui.settings.view.SettingsFragment
import com.arturostrowski.quiz.app.util.extension.removeFragment
import com.arturostrowski.quiz.app.util.extension.switchFragment
import com.arturostrowski.quiz.app.util.extension.switchFragmentAnim
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.nav_header_navigation.view.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMVPView, NavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDrawerMenu()

        presenter.onAttach(this)

        if(intent.getBooleanExtra("isUserLogged", false))
            supportFragmentManager.switchFragment(R.id.clContainer, NotificationsFragment().newInstance(), NotificationsFragment.TAG)
        else
            supportFragmentManager.switchFragment(R.id.clContainer, LoginFragment().newInstance(), LoginFragment.TAG)
    }

    override fun onBackPressed() {
        if(getTopFragment()?.tag.equals(ChatDetailsFragment.TAG)){
            supportFragmentManager.popBackStack()
            supportFragmentManager.switchFragment(R.id.clContainer, ChatFragment().newInstance(), ChatFragment.TAG)
        }else{
//            super.onBackPressed()
        }
    }

    private fun getTopFragment(): Fragment? {
        if (supportFragmentManager.backStackEntryCount == 0) {
            return null
        }
        val fragmentTag = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
        return supportFragmentManager.findFragmentByTag(fragmentTag)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }

    override fun onFragmentAttached() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemChat -> {
                presenter.onDrawerOptionChatClick()
            }
            R.id.navItemFindFriends -> {
                presenter.onDrawerOptionFindFriendsClick()
            }
            R.id.navItemFriends -> {
                presenter.onDrawerOptionFriendsClick()
            }
            R.id.navItemNotifications -> {
                presenter.onDrawerOptionNotificationsClick()
            }
            R.id.navItemLeaderboard -> {
                presenter.onDrawerOptionLeaderboardClick()
            }
            R.id.navItemSettings -> {
                presenter.onDrawerOptionSettingsClick()
            }
            R.id.navItemLogout -> {
                presenter.onDrawerOptionLogoutClick()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setUser(user: User) {
        this.user = user
        navView.getHeaderView(0).nav_name.text = "${user.firstName} ${user.lastName}"
        navView.getHeaderView(0).nav_email.text = "${user.coins} Coins"

        Picasso
                .get()
                .load(user.picture)
                .into(navView.getHeaderView(0).ivAvatar)
    }

    override fun inflateUserDetails(userDetails: Pair<String?, String?>) {
    }

    override fun openLoginActivity() {
    }

    override fun openAboutFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, ProfileFragment().newInstance(), ProfileFragment.TAG)
    }

    override fun openChatFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, ChatFragment().newInstance(), ChatFragment.TAG)
    }

    override fun openFindFriendsFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, FindFriendsFragment().newInstance(), FindFriendsFragment.TAG)
    }

    override fun openFriendsFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, FriendsFragment().newInstance(), FriendsFragment.TAG)
    }

    override fun openNotificationsFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, NotificationsFragment().newInstance(), NotificationsFragment.TAG)
    }

    override fun openLeaderboardFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, LeaderboardFragment().newInstance(), LeaderboardFragment.TAG)
    }

    override fun openSettingsFragment() {
        supportFragmentManager.switchFragmentAnim(R.id.clContainer, SettingsFragment().newInstance(), SettingsFragment.TAG)
    }
    override fun openFeedActivity() {
    }

    override fun logout() {
        user = null
        setToolbarVisibility(false)
        setToolbarTitle(getString(R.string.empty))
        supportFragmentManager.switchFragment(R.id.clContainer, LoginFragment().newInstance(), LoginFragment.TAG)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private fun setUpDrawerMenu() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        toolbar.navigationIcon = null

        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    fun setToolbarVisibility(isVisible: Boolean){
        toolbar.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    fun setToolbarTitle(title: String){
        tvToolbarTitle.text = title
    }

}
