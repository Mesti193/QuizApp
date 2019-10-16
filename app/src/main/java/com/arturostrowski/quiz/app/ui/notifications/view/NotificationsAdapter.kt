package com.arturostrowski.quiz.app.ui.notifications.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.data.network.response.Notifications
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationsAdapter(notifications: MutableList<Notifications>, val onClick: (Notifications) -> Unit, val unreadCount: (unreadCount: Long) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {

    private val notifications: MutableList<Notifications> = notifications

    private fun clearNotifications(){
        notifications.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) = holder.run {
        onBind(position, onClick, unreadCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NotificationsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_notification, parent, false))

    inner class NotificationsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun onBind(position: Int, onClick: (Notifications) -> Unit, unreadCount: (unreadCount: Long) -> Unit) {
            val notification = notifications[position]

            itemView.clNotification.setOnClickListener {
                onClick(notification)
                for (notification1 in notifications) {
                    notification1.isClicked = false
                }
                notification.isRead = true
                notification.isClicked = true
                var unreadNotifications: Long = 0
                for (notification2 in notifications) {
                    if(!notification2.isRead) unreadNotifications++
                }
                unreadCount(unreadNotifications)
                notifyDataSetChanged()
            }

            itemView.vClicked.visibility = if(notification.isClicked) View.VISIBLE else View.GONE
            itemView.clNotification.background = if(notification.isRead) ContextCompat.getDrawable(itemView.context, R.drawable.notification_background_read) else ContextCompat.getDrawable(itemView.context, R.drawable.notification_background_unread)
            itemView.ivNotification.background = when(notification.type.toInt()){
                1 -> ContextCompat.getDrawable(itemView.context, R.drawable.ic_achievement)
                2 -> ContextCompat.getDrawable(itemView.context, R.drawable.ic_quiz_drama)
                3 -> ContextCompat.getDrawable(itemView.context, R.drawable.ic_user)
                else -> ContextCompat.getDrawable(itemView.context, R.drawable.ic_user)
            }
            itemView.tvDescription.text = notification.description
            itemView.tvDate.text = notification.dateUTC

        }
    }
}
