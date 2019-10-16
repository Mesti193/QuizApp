package com.arturostrowski.quiz.app.ui.leaderboard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.GlobalLeaderboard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_leaderboard.view.*

class LeaderboardAdapter(leaderoardList: MutableList<GlobalLeaderboard>) : androidx.recyclerview.widget.RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private val leaderoardList: MutableList<GlobalLeaderboard> = leaderoardList

    override fun getItemCount() = leaderoardList.size

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) = holder.run {
        clear()
        onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LeaderboardViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_leaderboard, parent, false))

    inner class LeaderboardViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        fun clear() {
//            itemView.coverImageView.setImageDrawable(null)
//            itemView.titleTextView.text = ""
//            itemView.contentTextView.text = ""
        }

        fun onBind(position: Int) {

            val leaderboard = leaderoardList[position]
            if(leaderboard.picture.isEmpty()){
                itemView.ivProfile.setImageDrawable(itemView.context!!.getDrawable(R.drawable.ic_user))
            }else{
                Picasso
                        .get() // give it the context
                        .load(leaderboard.picture) // load the image
                        .into(itemView.ivProfile)
            }
            itemView.tvRankingPlace.text = position.plus(2).toString()
            itemView.tvName.text = "${leaderboard.firstName} ${leaderboard.lastName}"
            itemView.tvPoints.text = "Points: ${leaderboard.points}"


        }
    }
}
