package com.arturostrowski.quiz.app.ui.chat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.arturostrowski.quiz.app.di.showELog
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.chat.interactor.ChatMVPInteractor
import com.arturostrowski.quiz.app.ui.chat.presenter.ChatMVPPresenter
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsFragment
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.util.extension.addFragmentAnim
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject

class ChatFragment : BaseFragment(), ChatMVPView {
    companion object {
        val TAG = ChatFragment::class.java.simpleName
    }

    fun newInstance(): ChatFragment {
        val chatFragment = ChatFragment()
        return chatFragment
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: ChatMVPPresenter<ChatMVPView, ChatMVPInteractor>

    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_chat, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.chat_toolbar_title))
        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        presenter.onChatsPrepared(user)
    }

    override fun displayChats(chats: ArrayList<Chat>) {

        rvChats.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatsAdapter( chats ) {
                fragmentManager!!.addFragmentAnim(R.id.clContainer, ChatDetailsFragment().newInstance(it), ChatDetailsFragment.TAG)
            }
        }

    }

    override fun displayError(error: String) {
        showELog(error)
        Toast.makeText(getBaseActivity(), error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}