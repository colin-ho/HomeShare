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
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeshare.Model.Invitation;
import com.example.homeshare.Model.Response;
import com.example.homeshare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ResponsesAdapter extends RecyclerView.Adapter<ResponsesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Response> responses = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public ResponsesAdapter(Context context,FirebaseAuth mAuth,FirebaseFirestore db) {
        this.context = context;
        this.mAuth= mAuth;
        this.db = db;

    }

    public ResponsesAdapter() {
    }

    @NonNull
    @Override
    public ResponsesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_response, viewGroup, false);
        ResponsesAdapter.ViewHolder holder = new ResponsesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResponsesAdapter.ViewHolder holder, int position) {
        Response response = responses.get(position);
        holder.name.setText("Name: "+response.getResponderName());
        holder.message.setText("Message: "+response.getResponseMessage());
        holder.date.setText("Date: "+response.getDate().toString());

        if (response.getResponderUserID().equals(mAuth.getUid())){
            holder.acceptButton.setVisibility(View.GONE);
            holder.rejectButton.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
            holder.status.setText("Status: "+response.getStatus().toString());
        } else{
            if (response.getStatus().equals("rejected")){
                holder.rejectButton.setEnabled(false);
                holder.acceptButton.setVisibility(View.INVISIBLE);
                holder.rejectButton.setText("Response Rejected!");
            } else if (response.getStatus().equals("accepted")){
                holder.acceptButton.setEnabled(false);
                holder.rejectButton.setVisibility(View.INVISIBLE);
                holder.acceptButton.setText("Response Accepted!");
            }
        }

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Response").document(response.getResponseID()).update("status","rejected")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Rejecting response", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Rejecting response", "Error updating document", e);
                            }
                        });
                holder.rejectButton.setEnabled(false);
                holder.acceptButton.setVisibility(View.INVISIBLE);
                holder.rejectButton.setText("Response Rejected!");
            }
        });

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Response").document(response.getResponseID()).update("status","accepted")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Accepting response", "DocumentSnapshot successfully updated!");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Accepting response", "Error updating document", e);
                            }
                        });
                db.collection("Invitation").document(response.getInvitationID()).update("roommates", FieldValue.increment(-1))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Decrementing Roommates", "DocumentSnapshot successfully updated!");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Decrementing Roommates", "Error updating document", e);
                            }
                        });
                holder.acceptButton.setEnabled(false);
                holder.rejectButton.setVisibility(View.INVISIBLE);
                holder.acceptButton.setText("Response accepted!");
            }
        });
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView message;
        private TextView date;
        private TextView status;

        private Button acceptButton;
        private Button rejectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.responseItemName);
            message = (TextView) itemView.findViewById(R.id.responseItemMessage);
            date = (TextView) itemView.findViewById(R.id.responseItemDate);
            acceptButton = (Button) itemView.findViewById(R.id.responseItemAccept);
            rejectButton = (Button) itemView.findViewById(R.id.responseItemReject);
            status = (TextView) itemView.findViewById(R.id.responseItemStatus);
        }
    }
}

