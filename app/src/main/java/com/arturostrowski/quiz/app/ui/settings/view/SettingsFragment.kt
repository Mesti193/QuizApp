package com.arturostrowski.quiz.app.ui.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.login.view.LoginFragment
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.ui.settings.interactor.SettingsMVPInteractor
import com.arturostrowski.quiz.app.ui.settings.presenter.SettingsMVPPresenter
import com.arturostrowski.quiz.app.util.extension.switchFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment(), SettingsMVPView {
    companion object {
        val TAG = SettingsFragment::class.java.simpleName
    }

    fun newInstance(): SettingsFragment {
        val settingsFragment = SettingsFragment()
        return settingsFragment
    }

    @Inject
    internal lateinit var layoutManager: androidx.recyclerview.widget.LinearLayoutManager
    @Inject
    internal lateinit var presenter: SettingsMVPPresenter<SettingsMVPView, SettingsMVPInteractor>

    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_settings, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.settings_toolbar_title))
        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        tvEmailDesc.text = user.email
        btLogout.setOnClickListener {
            presenter.onLogoutButtonClick()
        }
    }

    override fun logout() {
        user = null
        (activity as MainActivity).setToolbarVisibility(false)
        (activity as MainActivity).setToolbarTitle(getString(R.string.empty))
        fragmentManager!!.switchFragment(R.id.clContainer, LoginFragment().newInstance(), LoginFragment.TAG)
    }

    override fun displayError(error: String) {

    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}