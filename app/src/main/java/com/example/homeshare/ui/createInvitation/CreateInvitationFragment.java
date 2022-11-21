package com.example.homeshare.ui.createInvitation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.homeshare.CreateAccountActivity;
import com.example.homeshare.HomeActivity;
import com.example.homeshare.Model.Invitation;
import com.example.homeshare.R;
import com.example.homeshare.databinding.FragmentCreateInvitationBinding;
import com.example.homeshare.utils.CreateInvitationUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class CreateInvitationFragment extends Fragment {

    private FragmentCreateInvitationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateInvitationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button createButton = getView().findViewById(R.id.createInvitationButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = ((EditText) getView().findViewById(R.id.createInvitationLocation)).getText().toString();
                String description = ((EditText) getView().findViewById(R.id.createInvitationDescription)).getText().toString();
                String day = ((EditText) getView().findViewById(R.id.createInvitationDay)).getText().toString();
                String month = ((EditText) getView().findViewById(R.id.createInvitationMonth)).getText().toString();
                String year = ((EditText) getView().findViewById(R.id.createInvitationYear)).getText().toString();
                String price = ((EditText) getView().findViewById(R.id.createInvitationPrice)).getText().toString();
                String roommates = ((EditText) getView().findViewById(R.id.createInvitationRoommates)).getText().toString();

                String errorMessage = CreateInvitationUtils.validateInputFields(description, location, day, month, year, price, roommates);
                if (!errorMessage.isEmpty()) {
                    Toast.makeText(getActivity(), errorMessage,
                            Toast.LENGTH_LONG).show();
                    return;
                }

                CheckBox gas = (CheckBox) getView().findViewById(R.id.checkBoxGas);
                CheckBox wifi = (CheckBox) getView().findViewById(R.id.checkBoxWifi);
                CheckBox water = (CheckBox) getView().findViewById(R.id.checkBoxWater);
                CheckBox electricity = (CheckBox) getView().findViewById(R.id.checkBoxElectricity);

                String creatorUserID = ((HomeActivity) getActivity()).getmAuth().getCurrentUser().getUid();
                String name = ((HomeActivity) getActivity()).getUser().getFullName();

                Map<String, Object> utilities = new HashMap<>();
                utilities.put("gas", gas.isChecked());
                utilities.put("wifi", wifi.isChecked());
                utilities.put("water", water.isChecked());
                utilities.put("electricity", electricity.isChecked());

                Invitation invitation = new Invitation(name, creatorUserID, description, location, day, month, year, price, Integer.parseInt(roommates), utilities);

                // Add a new document with a generated ID
                ((HomeActivity) getActivity()).getDb().collection("Invitation")
                        .add(invitation)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Create Invitation", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Navigation.findNavController(view).navigate(R.id.navigation_home);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Create Invitation", "Error adding document", e);
                                Toast.makeText(getActivity(), "Error creating invitation",
                                        Toast.LENGTH_LONG).show();
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