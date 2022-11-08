package com.example.homeshare.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.Model.Invitation;
import com.example.homeshare.R;
import com.example.homeshare.adapters.InvitationsAdapter;
import com.example.homeshare.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.feedRecyclerView);
        InvitationsAdapter adapter = new InvitationsAdapter(getContext()
                ,((HomeActivity)getActivity()).getDb(),((HomeActivity)getActivity()).getmAuth());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        showInvitationsFromFirebase(adapter);

        return view;
    }

    public void showInvitationsFromFirebase(InvitationsAdapter adapter){
        ArrayList<Invitation> invitations = new ArrayList<>();

        ((HomeActivity)getActivity()).getDb().collection("Invitation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Get Invitations", document.getId() + " => " + document.getData());
                                Invitation invitation = document.toObject(Invitation.class);
                                invitation.setInvitationID(document.getId());
                                invitations.add(invitation);
                            }
                            adapter.setInvitations(invitations);
                        } else {
                            Log.d("Get Invitations", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}