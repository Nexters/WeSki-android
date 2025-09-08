package com.dieski.weski.service

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dieski.weski.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.UUID

class WeskiFirebaseMessagingService : FirebaseMessagingService() {

    private val channelId = "fcm_default_channel"

    /**
     * FCM 토큰이 새로 생성되거나 갱신될 때 호출
     */
    override fun onNewToken(token: String) {
        Log.d("FCM_Service", "새로운 FCM 토큰: $token")

        // 서버로 새 토큰 전송
        sendTokenToServer(token)
    }

    /**
     * FCM 메시지를 받았을 때 호출
     */
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            sendNotification(it.title, it.body)
        }
    }

    /* 알림 생성 메서드 */
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun sendNotification(
        title: String?,
        message: String?
    ) {
        Log.w("Test@@@" , "sendNotification: $title / $message")
        NotificationManagerCompat.from(this)
            .notify(UUID.randomUUID().hashCode(), createNotification(title, message))
    }


    private fun createNotification(
        title: String?,
        message: String?): Notification {

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this,
            0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(com.dieski.weski.presentation.R.drawable.ic_weski_logo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) //알림 눌렀을 때 실행할 Intent 설정
            .setAutoCancel(true)

        return notificationBuilder.build()
    }

    /**
     * 서버로 토큰 전송 (구현 필요)
     */
    private fun sendTokenToServer(token: String) {
        // TODO: 여기에 서버로 토큰을 전송하는 로직 구현
        Log.d("FCM_Service", "서버로 새 토큰 전송 필요: $token")
    }
}