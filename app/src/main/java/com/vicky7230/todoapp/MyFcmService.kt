package com.vicky7230.todoapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vicky7230.todoapp.ui.main.activity.MainActivity
import timber.log.Timber

class MyFcmService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Handle data message
        message.data.let { data: MutableMap<String, String> ->
            val title = data["title"] ?: "Default Title"
            val body = data["content"] ?: "Default Body"
            //create notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            }
            val notification = createNotification(title, body)
            val notificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java)
            notificationManager?.notify(1001, notification)
        }
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        val channel = NotificationChannel("test", "Testing", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
        return channel
    }

    private fun createNotification(title: String, body: String): Notification {
        val notification = NotificationCompat.Builder(this, "test")
            .setContentText(body)
            .setContentTitle(title)
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        return notification
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("Fcm token $token")
    }
}