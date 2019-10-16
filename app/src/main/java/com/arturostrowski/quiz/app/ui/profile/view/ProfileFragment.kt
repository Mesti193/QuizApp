package com.arturostrowski.quiz.app.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }

    fun newInstance(): ProfileFragment {
        return ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_profile, container, false)

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.empty))
        Picasso
                .get() // give it the context
                .load("https://i.imgur.com/H981AN7.jpg") // load the image
                .into(ivProfile)
//        navBackBtn.setOnClickListener { getBaseActivity()?.onFragmentDetached(TAG) }
    }
}