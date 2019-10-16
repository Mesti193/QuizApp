package com.arturostrowski.quiz.app.ui.friends.view

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.Friend
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(private val context: Context, private val friends: MutableList<Friend>, val onClick: (Friend) -> Unit, val onClickDelete: (Friend) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) = holder.run {
        onBind(position, onClick, onClickDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_friend, parent, false))

    inner class FriendsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun onBind(position: Int, onClick: (Friend) -> Unit, onClickDelete: (Friend) -> Unit) {
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
            itemView.tvUserDetails.text = "Level: ${friend.level}, Points: ${friend.points}"
            itemView.clFriend.setOnClickListener {
                onClick(friend)
            }
            itemView.ivMore.setOnClickListener {
                showPopup(it, friend)
            }
        }
    }


    private fun showPopup(view: View, friend: Friend) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(context, view)
        popup.inflate(R.menu.header_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.itemDelete -> {
                    onClickDelete(friend)
                }
            }
            true
        })
        popup.show()
    }
}
