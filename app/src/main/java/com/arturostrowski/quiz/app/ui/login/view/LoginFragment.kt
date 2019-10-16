package com.arturostrowski.quiz.app.ui.login.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.login.interactor.LoginMVPInteractor
import com.arturostrowski.quiz.app.ui.login.presenter.LoginMVPPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.ui.notifications.view.NotificationsFragment
import com.arturostrowski.quiz.app.util.extension.switchFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginMVPView {
    companion object {
        val TAG = LoginFragment::class.java.simpleName
    }

    fun newInstance(): LoginFragment {
        val loginFragment = LoginFragment()
        return loginFragment
    }

    @Inject
    internal lateinit var layoutManager: androidx.recyclerview.widget.LinearLayoutManager
    @Inject
    internal lateinit var presenter: LoginMVPPresenter<LoginMVPView, LoginMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_login, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {

        (activity as MainActivity).setToolbarVisibility(false)
        btContinue.setOnClickListener {
            presenter.onServerLoginClicked(etEmail.text.toString(), etPassword.text.toString())
        }

    }

    override fun openMainFragment() {
        fragmentManager?.switchFragment(R.id.clContainer, NotificationsFragment().newInstance(), NotificationsFragment.TAG)
    }

    override fun showValidationMessage(stringRes: Int) {
        Toast.makeText(getBaseActivity(), getString(stringRes), Toast.LENGTH_LONG).show()
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