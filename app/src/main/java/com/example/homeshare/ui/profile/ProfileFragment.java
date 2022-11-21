package com.example.homeshare.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.MainActivity;
import com.example.homeshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String UID = ((HomeActivity)getActivity()).getmAuth().getCurrentUser().getUid();
        EditText name = (EditText) view.findViewById(R.id.personName);
        EditText phone = (EditText) view.findViewById(R.id.phoneNumber);
        EditText description = (EditText) view.findViewById(R.id.description);

        DocumentReference docRef = ((HomeActivity)getActivity()).getDb().collection("User").document(UID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Displaying Profile", "Profile successfully displayed");
                        name.setText(document.getData().get("fullName").toString());
                        phone.setText(document.getData().get("contactNumber").toString());
                        description.setText(document.getData().get("about").toString());
                    } else {
                        Log.d("Displaying Profile", "Profile document doesn't exist");
                    }
                } else {
                    Log.d("get failed with ", String.valueOf(task.getException()));
                }
            }
        });

        Button logoutButton = view.findViewById(R.id.logoutProfileButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                System.out.println("logging out");
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button createButton = getView().findViewById(R.id.updateProfile);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) getView().findViewById(R.id.personName);
                EditText phone = (EditText) getView().findViewById(R.id.phoneNumber);
                EditText description = (EditText) getView().findViewById(R.id.description);

                Map<String, Object> profileUpdate = new HashMap<>();
                profileUpdate.put("fullName", name.getText().toString());
                profileUpdate.put("contactNumber", phone.getText().toString());
                profileUpdate.put("about", description.getText().toString());

                String UID = ((HomeActivity)getActivity()).getmAuth().getCurrentUser().getUid();

                // Add a new document with a generated ID
                ((HomeActivity)getActivity()).getDb().collection("User").document(UID)
                        .update("fullName",name.getText().toString(),"contactNumber",phone.getText().toString(),"about",description.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Updating Profile", "Profile successfully updated");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Update Profile", "Error updating document", e);
                                Toast.makeText(getActivity(), "Error updating profile",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}