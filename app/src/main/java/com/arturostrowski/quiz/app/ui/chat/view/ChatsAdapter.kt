package com.arturostrowski.quiz.app.ui.chat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_chats.view.*

class ChatsAdapter(private val chats: MutableList<Chat>, val onClick: (Chat) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    override fun getItemCount() = chats.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) = holder.run {
        onBind(position, onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_chats, parent, false))

    inner class ChatViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun onBind(position: Int, onClick: (Chat) -> Unit) {
            val chat = chats[position]

            if(chat.picture.isEmpty()){
                itemView.ivUserPhoto.setImageDrawable(itemView.context!!.getDrawable(R.drawable.ic_user))
            }else{
                Picasso
                        .get() // give it the context
                        .load(chat.picture) // load the image
                        .into(itemView.ivUserPhoto)
            }

            itemView.tvUserName.text = "${chat.firstName} ${chat.lastName}"
            itemView.tvLastMessage.text = chat.lastMessage
            itemView.tvDate.text = chat.startedDateTime
            itemView.clChat.setOnClickListener { onClick(chat) }
        }
    }
}
