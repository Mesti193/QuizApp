package com.arturostrowski.quiz.app.ui.chat_details.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.ChatContent
import com.arturostrowski.quiz.app.ui.base.view.ChatViewHolder
import kotlinx.android.synthetic.main.item_chat_content_left.view.*
import kotlinx.android.synthetic.main.item_chat_content_right.view.*

class ChatDetailsAdapter(private val user: User, private val chatContent: MutableList<ChatContent>, val onClick: (ChatContent) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_USER = 0
        const val TYPE_FRIEND = 1
    }

    fun addList(contentList: MutableList<ChatContent>){
        chatContent.addAll(contentList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if ( chatContent[position].userID == user.userID) TYPE_USER else TYPE_FRIEND
    }

    override fun getItemCount() = chatContent.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.run {
        (holder as ChatViewHolder).bindViews(chatContent[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            TYPE_USER -> UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_content_right, parent, false))
            else -> FriendViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_content_left, parent, false))
        }
        return viewHolder
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), ChatViewHolder {
        override fun bindViews(chatContent: ChatContent) {
            itemView.tvMessageRight.text = chatContent.message
        }
//        fun onBind(position: Int, onClick: (ChatContent) -> Unit) {
//            val content = chatContent[position]
//        }
    }

    inner class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view), ChatViewHolder {
        override fun bindViews(chatContent: ChatContent) {
            itemView.tvMessageLeft.text = chatContent.message
        }
//        fun onBind(position: Int, onClick: (ChatContent) -> Unit) {
//            val content = chatContent[position]
//            itemView.tvMessageLeft.text = content.message
//        }
    }
}