package com.example.homeshare.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ManagementTool {

    private static final String TAG = "ManagementTool";

    private Number UserId;
    private FirebaseFirestore db;

    public ManagementTool(Number userId, FirebaseFirestore db) {
        UserId = userId;
        this.db = db;
    }

    public Invitation createInvitation(Invitation invitation) {
        db.collection("Invitation")
                .add(invitation)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        return invitation;
    }

    public void deleteInvitation(String invitationId) {
        db.collection("Invitation").document(invitationId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public Response acceptInvitation(String invitationId, String responseMessage){
        return null;
    }

    public void rejectInvitation(String invitationId){
        return;
    }

    public void deleteResponse(String responseId) {
        db.collection("Response").document(responseId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public void acceptResponse(String responseId) {
        return;
    }

    public void rejectResponse(String responseId) {
        return;
    }


}
