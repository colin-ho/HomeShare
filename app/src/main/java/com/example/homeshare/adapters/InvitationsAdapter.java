package com.example.homeshare.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeshare.Model.Invitation;
import com.example.homeshare.Model.Response;
import com.example.homeshare.Model.User;
import com.example.homeshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class InvitationsAdapter extends RecyclerView.Adapter<InvitationsAdapter.ViewHolder> {

    private ArrayList<Invitation> invitations = new ArrayList<>();
    private Context context;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InvitationsAdapter(Context context, FirebaseFirestore db, FirebaseAuth auth,User user) {
        this.context = context;
        this.db = db;
        this.mAuth = auth;
        this.user = user;
    }

    public InvitationsAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_invitation, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Invitation invitation = invitations.get(position);
        holder.name.setText("Name: "+invitation.getName());
        holder.location.setText("Location: "+invitation.getLocation());
        holder.deadline.setText("Deadline: "+invitation.getDay()+"/"+invitation.getMonth()+"/"+invitation.getYear());
        holder.price.setText("Price: "+invitation.getPrice());
        holder.description.setText("Description: "+invitation.getDescription());
        holder.spots.setText("Spots left: "+invitation.getRoommates());
        StringBuilder utilitiesString = new StringBuilder();
        invitation.getUtilities().forEach((k,v)->{
            if (v.equals(true)){
                utilitiesString.append(k+" ");
            }
        });
        holder.utilities.setText("Utilities: "+utilitiesString.toString());

        if (user.getRejectedInvitations().contains(invitation.getInvitationID())){
            holder.rejectButton.setVisibility(View.GONE);
            holder.acceptButton.setEnabled(false);
            holder.acceptButton.setText("Invitation Accepted!");
        }

        if (user.getAcceptedInvitations().contains(invitation.getInvitationID())){
            holder.rejectButton.setVisibility(View.GONE);
            holder.acceptButton.setEnabled(false);
            holder.acceptButton.setText("Invitation Accepted!");
        }

        if (invitation.getCreatorUserID().equals(mAuth.getUid())){
            holder.name.setText("My invitation");
        }

        if (invitation.getRoommates()==0){
            holder.rejectButton.setVisibility(View.GONE);
            holder.acceptButton.setEnabled(false);
            holder.acceptButton.setText("Spots filled");
        }

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (invitation.getCreatorUserID().equals(mAuth.getCurrentUser().getUid())){
                    Toast.makeText(view.getContext(), "Cannot accept your own invitation",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String message = holder.message.getText().toString();
                acceptInvitation(view,message, invitation.getInvitationID(),invitation.getCreatorUserID());
                holder.rejectButton.setVisibility(View.GONE);
                holder.acceptButton.setEnabled(false);
                holder.acceptButton.setText("Invitation Accepted!");
            }
        });

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (invitation.getCreatorUserID().equals(mAuth.getCurrentUser().getUid())){
                    Toast.makeText(view.getContext(), "Cannot reject your own invitation",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                rejectInvitation(view, invitation.getInvitationID());
                holder.rejectButton.setVisibility(View.GONE);
                holder.acceptButton.setEnabled(false);
                holder.acceptButton.setText("Invitation Rejected!");
            }
        });
    }

    public void acceptInvitation(View view, String message,String invitationID,String creatorUserID){
        Response response = new Response(message,new Date(),invitationID,"pending",mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getDisplayName(),creatorUserID);
        db.collection("Response").add(response)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Accept Invitation", "Resposne added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Accept Invitation", "Error adding document", e);
                        Toast.makeText(view.getContext(), "Error accepting invitation",
                                Toast.LENGTH_LONG).show();
                    }
                });

        db.collection("User").document(mAuth.getCurrentUser().getUid()).update("acceptedInvitations", FieldValue.arrayUnion(invitationID))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("reject invitation", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("reject invitation", "Error writing document", e);
                    }
                });
    }

    public void rejectInvitation(View view, String invitationID){
        db.collection("User").document(mAuth.getCurrentUser().getUid()).update("rejectedInvitations", FieldValue.arrayUnion(invitationID))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("reject invitation", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("reject invitation", "Error writing document", e);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return invitations.size();
    }

    public void setInvitations(ArrayList<Invitation> invitations) {
        this.invitations = invitations;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView location;
        private TextView price;
        private TextView description;
        private TextView deadline;
        private TextView utilities;
        private TextView spots;
        private Button acceptButton;
        private Button rejectButton;
        private TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.invitationItemName);
            location = (TextView) itemView.findViewById(R.id.invitationItemLocation);
            price = (TextView) itemView.findViewById(R.id.invitationItemPrice);
            description= (TextView) itemView.findViewById(R.id.invitationItemDescription);
            deadline = (TextView) itemView.findViewById(R.id.invitationItemDeadline);
            utilities = (TextView) itemView.findViewById(R.id.invitationItemUtilities);
            acceptButton = (Button) itemView.findViewById(R.id.invitationItemAccept);
            rejectButton = (Button) itemView.findViewById(R.id.invitationItemReject);
            message = (EditText) itemView.findViewById(R.id.invitationItemMessage);
            spots = (TextView) itemView.findViewById(R.id.invitationItemSpots);
        }
    }
}
