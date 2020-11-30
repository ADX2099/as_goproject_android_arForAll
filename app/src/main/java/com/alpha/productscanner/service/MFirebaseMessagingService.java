package com.alpha.productscanner.service;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import androidx.core.app.NotificationCompat;

import android.util.Log;

import com.alpha.productscanner.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);

        String titulo = "";

//        if (TextUtils.isEmpty(message.getNotification().getTitle())) {

        titulo = "SIN TITULO";
        //     }




        Log.e("PUSH--->", "message.getNotification().getTitle(): " + message.getNotification().getTitle());
        Log.e("PUSH--->", "message.getNotification().getBody(): " + message.getNotification().getBody());


        Log.e("PUSH--->", message.getData().toString());
        Log.e("PUSH--->", "TITULO: " + message.getData().get("title"));
        Log.e("PUSH--->", "BODY: " + message.getData().get("body"));
        Log.e("PUSH--->", "LATITUD: " + message.getData().get("latitud"));
        Log.e("PUSH--->", "LONGUITUD: " + message.getData().get("longuitud"));





        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody()  ) //setContentText(message.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}