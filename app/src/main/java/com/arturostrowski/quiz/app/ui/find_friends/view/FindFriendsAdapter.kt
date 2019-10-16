package com.arturostrowski.quiz.app.ui.find_friends.view

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.SearchFriends
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_friend.view.*

class FindFriendsAdapter(private val context: Context, private val friends: MutableList<SearchFriends>, val onClick: (SearchFriends) -> Unit, val onClickAdd: (SearchFriends) -> Unit, val onClickDelete: (SearchFriends) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<FindFriendsAdapter.FindFriendsViewHolder>() {

    override fun getItemCount() = friends.size

    fun clearList(){
        friends.clear()
        notifyDataSetChanged()
    }

    fun addList(friends: MutableList<SearchFriends>){
        this.friends.clear()
        this.friends.addAll(friends)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: FindFriendsViewHolder, position: Int) = holder.run {
        onBind(position, onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FindFriendsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_search_friends, parent, false))

    inner class FindFriendsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun onBind(position: Int, onClick: (SearchFriends) -> Unit) {
            val friend = friends.get(position)

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

            itemView.ivMore.visibility = when(friend.typeID.toInt()){
                1, 2, 3 -> View.GONE
                else -> View.VISIBLE
            }

            itemView.ivMore.setOnClickListener {
                showPopup(it, friend)
            }
        }
    }


    private fun showPopup(view: View, friend: SearchFriends) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(context, view)
        popup.inflate(R.menu.header_menu_find_friends)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.itemAdd -> {
                    onClickAdd(friend)
                }
            }
            true
        })
        popup.show()
    }
}
