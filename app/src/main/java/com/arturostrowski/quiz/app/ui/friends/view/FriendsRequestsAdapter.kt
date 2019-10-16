package com.arturostrowski.quiz.app.ui.friends.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.Friend
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_friend_requests.view.*

class FriendsRequestsAdapter(private val friends: MutableList<Friend>, val onClickConfirm: (Friend) -> Unit, val onClickDelete: (Friend) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<FriendsRequestsAdapter.FriendsRequestsViewHolder>() {

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: FriendsRequestsViewHolder, position: Int) = holder.run {
        onBind(position, onClickConfirm, onClickDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendsRequestsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_friend_requests, parent, false))

    inner class FriendsRequestsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun onBind(position: Int, onClickConfirm: (Friend) -> Unit, onClickDelete: (Friend) -> Unit) {
            val friend = friends[position]

            if(friend.picture.isEmpty()){
                itemView.ivUserPhoto.setImageDrawable(itemView.context!!.getDrawable(R.drawable.ic_user))
            }else{
                Picasso
                        .get() // give it the context
                        .load(friend.picture) // load the image
                        .into(itemView.ivUserPhoto)
            }
            itemView.tvUserName.text = "${friend.firstName} ${friend.lastName}"
            itemView.btConfirm.setOnClickListener {
                onClickConfirm(friend)
            }
            itemView.btDelete.setOnClickListener {
                onClickDelete(friend)
            }
        }
    }
}
