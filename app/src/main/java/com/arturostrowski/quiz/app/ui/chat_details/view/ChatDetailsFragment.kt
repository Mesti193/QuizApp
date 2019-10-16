package com.arturostrowski.quiz.app.ui.chat_details.view

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.arturostrowski.quiz.app.data.network.response.ChatContent
import com.arturostrowski.quiz.app.data.network.response.UserDetail
import com.arturostrowski.quiz.app.di.showELog
import com.arturostrowski.quiz.app.ui.base.view.BaseFragment
import com.arturostrowski.quiz.app.ui.chat_details.interactor.ChatDetailsMVPInteractor
import com.arturostrowski.quiz.app.ui.chat_details.presenter.ChatDetailsMVPPresenter
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_chat_details.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate


class ChatDetailsFragment : BaseFragment(), ChatDetailsMVPView {
    companion object {
        val TAG = ChatDetailsFragment::class.java.simpleName
    }

    private lateinit var chat: Chat
    private var timestamp: Long = 0

    fun newInstance(chat: Chat): ChatDetailsFragment {
        val chatDetailsFragment = ChatDetailsFragment()
        chatDetailsFragment.chat = chat
        return chatDetailsFragment
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: ChatDetailsMVPPresenter<ChatDetailsMVPView, ChatDetailsMVPInteractor>

    private var user: User? = null

    private lateinit var chatAdapter: ChatDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_chat_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).setToolbarVisibility(true)
        (activity as MainActivity).setToolbarTitle(getString(R.string.chat_details_toolbar_title))


        presenter.loadUserFromDatabase()
    }

    override fun getUser(user: User) {
        this.user = user
        (activity as MainActivity).setUser(user)
        chatAdapter = ChatDetailsAdapter(user!!, mutableListOf()){}
        rvChatContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
        etMessage.imeOptions = EditorInfo.IME_ACTION_DONE
        etMessage.setRawInputType(InputType.TYPE_CLASS_TEXT)
        etMessage.setOnEditorActionListener { _, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                presenter.onAddedChatContent(user, chat, etMessage.text.toString(), timestamp)
                etMessage.setText("")
                true
            } else {
                false
            }
        }
        Timer().scheduleAtFixedRate(0, 5000) {
                presenter.onChatContentPrepared(user, chat, timestamp)
        }
    }

    override fun displayUser(user: UserDetail) {
        if(user.picture.isEmpty()){
            ivUserPhoto.setImageDrawable(context!!.getDrawable(R.drawable.ic_user))
        }else{
            Picasso
                    .get() // give it the context
                    .load(user.picture) // load the image
                    .into(ivUserPhoto)
        }

        tvUserName.text = "${user.firstName} ${user.lastName}"
    }

    override fun displayChatContent(chatContent: ArrayList<ChatContent>, timestamp: Long) {
        this.timestamp = timestamp

        if(chatContent.size > 0){
            chatAdapter.addList(chatContent)
            rvChatContent.scrollToPosition(chatAdapter.itemCount - 1)
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