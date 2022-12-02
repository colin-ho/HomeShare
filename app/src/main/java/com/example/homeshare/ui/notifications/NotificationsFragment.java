package com.example.homeshare.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.Model.Notification;
import com.example.homeshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        TextView message = (TextView) view.findViewById(R.id.notificationMessage1);

        showResponsesFromFirebase(message);

        return view;
    }

    public void showResponsesFromFirebase(TextView message){
        if (((HomeActivity)getActivity()).getmAuth().getCurrentUser() == null){
            return;
        }
        ArrayList<Notification> acceptedNotifications = new ArrayList<>();

        ((HomeActivity)getActivity()).getDb().collection("Response").whereEqualTo("responderUserID",
                        ((HomeActivity)getActivity()).getmAuth().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Get Notifications to Me", document.getId() + " => " + document.getData());
                                Notification notification = document.toObject(Notification.class);
                                acceptedNotifications.add(notification);
                                notification.setResponseID(document.getId());
                                Log.d("notification test", notification.toString());
                            }
                            String notifications = "";
                            acceptedNotifications.removeIf(notification -> notification.getStatus() == "pending");
                            for (Notification notification : acceptedNotifications ) {
                                notifications += "     " + notification.getResponderName() + " " +
                                        notification.getStatus() + " your invitation\n\n\n";
                            }

                            message.setText(notifications);

                        } else {
                            Log.d("Get Notifications to Me", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}