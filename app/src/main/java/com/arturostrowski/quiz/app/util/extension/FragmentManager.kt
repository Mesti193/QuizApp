package com.arturostrowski.quiz.app.util.extension

import com.arturostrowski.quiz.app.R

internal fun androidx.fragment.app.FragmentManager.removeFragment(tag: String,
                                                                  slideIn: Int = R.anim.slide_left,
                                                                  slideOut: Int = R.anim.slide_right) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .remove(this.findFragmentByTag(tag)!!)
            .commitNow()
}

internal fun androidx.fragment.app.FragmentManager.addFragment(containerViewId: Int,
                                                               fragment: androidx.fragment.app.Fragment,
                                                               tag: String,
                                                               slideIn: Int = R.anim.slide_left,
                                                               slideOut: Int = R.anim.slide_right) {
    this.beginTransaction().disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .add(containerViewId, fragment, tag)
            .commit()
}

internal fun androidx.fragment.app.FragmentManager.addFragmentAnim(containerViewId: Int,
                                                               fragment: androidx.fragment.app.Fragment,
                                                               tag: String,
                                                               slideIn: Int = R.anim.slide_left,
                                                               slideOut: Int = R.anim.slide_right) {
    this.beginTransaction()
            .addToBackStack(tag)
            .add(containerViewId, fragment, tag)
            .commit()
}

internal fun androidx.fragment.app.FragmentManager.switchFragmentAnim(containerViewId: Int,
                                                               fragment: androidx.fragment.app.Fragment,
                                                               tag: String,
                                                               slideIn: Int = R.anim.slide_left,
                                                               slideOut: Int = R.anim.slide_right) {
    this.beginTransaction().disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .replace(containerViewId, fragment, tag)
            .commit()
}

internal fun androidx.fragment.app.FragmentManager.switchFragment(containerViewId: Int,
                                                                  fragment: androidx.fragment.app.Fragment,
                                                                  tag: String) {
    this.beginTransaction().disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .commit()
}


