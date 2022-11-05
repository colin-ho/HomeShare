package com.example.homeshare.ui.createInvitation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.LoginActivity;
import com.example.homeshare.R;
import com.example.homeshare.databinding.FragmentCreateInvitationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class CreateInvitationFragment extends Fragment {

    private FragmentCreateInvitationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreateInvitationViewModel createInvitationViewModel =
                new ViewModelProvider(this).get(CreateInvitationViewModel.class);

        binding = FragmentCreateInvitationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCreateInvitations;
        createInvitationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button createButton = getView().findViewById(R.id.createInvitationButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText location = (EditText) getView().findViewById(R.id.createInvitationLocation);
                EditText description = (EditText) getView().findViewById(R.id.createInvitationDescription);
                EditText day = (EditText) getView().findViewById(R.id.createInvitationDay);
                EditText month = (EditText) getView().findViewById(R.id.createInvitationMonth);
                EditText year = (EditText) getView().findViewById(R.id.createInvitationYear);
                EditText price = (EditText) getView().findViewById(R.id.createInvitationPrice);
                EditText beds = (EditText) getView().findViewById(R.id.createInvitationBeds);

                CheckBox gas = (CheckBox) getView().findViewById(R.id.checkBoxGas);
                CheckBox wifi = (CheckBox) getView().findViewById(R.id.checkBoxWifi);
                CheckBox water = (CheckBox) getView().findViewById(R.id.checkBoxWater);
                CheckBox electricity = (CheckBox) getView().findViewById(R.id.checkBoxElectricity);

                Map<String, Object> invitation = new HashMap<>();
                invitation.put("location", location.getText().toString());
                invitation.put("description", description.getText().toString());
                invitation.put("day", day.getText().toString());
                invitation.put("month", month.getText().toString());
                invitation.put("year", year.getText().toString());
                invitation.put("price", price.getText().toString());
                invitation.put("beds", beds.getText().toString());
                invitation.put("gas", gas.isChecked());
                invitation.put("wifi", wifi.isChecked());
                invitation.put("water", water.isChecked());
                invitation.put("electricity", electricity.isChecked());

                // Add a new document with a generated ID
                ((HomeActivity)getActivity()).db.collection("Invitation")
                        .add(invitation)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Create Invitation", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Create Invitation", "Error adding document", e);
                            }
                        });
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}