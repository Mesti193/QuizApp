package com.arturostrowski.quiz.app.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import com.arturostrowski.quiz.app.util.CommonUtil
import dagger.android.support.AndroidSupportInjection

abstract class BaseDialogView : androidx.fragment.app.DialogFragment(), DialogMVPView {

    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }

    override fun show(fragmentManager: androidx.fragment.app.FragmentManager, tag: String) {
        val transaction = fragmentManager.beginTransaction()

        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun dismissDialog(tag: String) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    private fun getBaseActivity(): BaseActivity? {
        return parentActivity
    }

}