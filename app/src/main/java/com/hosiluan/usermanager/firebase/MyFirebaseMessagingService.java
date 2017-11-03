package com.hosiluan.usermanager.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hosiluan.usermanager.R;
import com.hosiluan.usermanager.SocialNetworkActivity;

/**
 * Created by User on 11/2/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Luan", "From: " + remoteMessage.getFrom());
        Log.d("Luan", "Notification Message Body: " + remoteMessage.getNotification().getBody());

        // Create Notification
        Intent intent = new Intent(this, SocialNetworkActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410,
                intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_about)
                .setContentTitle("Message")
                .setContentText(remoteMessage.getNotification().getBody() + " from: "
                        + remoteMessage.getFrom())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1410, notificationBuilder.build());


    }
}
