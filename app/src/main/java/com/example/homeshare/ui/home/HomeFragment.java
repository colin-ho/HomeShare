package com.example.homeshare.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.R;
import com.example.homeshare.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ((HomeActivity)getActivity()).getDb().collection("Invitation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Get Invitations", document.getId() + " => " + document.getData());

                                ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.item_invitation, null, false);

                                ((TextView) layout.findViewById(R.id.invitationItemLocation)).setText("Location: "+document.getData().get("location").toString());
                                ((TextView) layout.findViewById(R.id.invitationItemPrice)).setText("Price: "+document.getData().get("price").toString());
                                ((TextView) layout.findViewById(R.id.invitationItemDescription)).setText("Description: "+document.getData().get("description").toString());
                                ((TextView) layout.findViewById(R.id.invitationItemName)).setText("Name: "+document.getData().get("name").toString());
                                ((TextView) layout.findViewById(R.id.invitationItemDeadline)).setText("Deadline: "+document.getData().get("day").toString()+"/"+document.getData().get("month").toString()+"/"+document.getData().get("year").toString());

                                LinearLayout linear = (LinearLayout) getActivity().findViewById(R.id.feed);
                                linear.addView(layout);
                            }
                        } else {
                            Log.d("Get Invitations", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}