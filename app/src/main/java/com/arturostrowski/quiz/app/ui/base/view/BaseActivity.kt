package com.arturostrowski.quiz.app.ui.base.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arturostrowski.quiz.app.util.CommonUtil
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), MVPView, BaseFragment.CallBack {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    private fun performDI() = AndroidInjection.inject(this)

}