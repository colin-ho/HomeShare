package com.example.homeshare.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.MainActivity;
import com.example.homeshare.Model.Invitation;
import com.example.homeshare.Model.User;
import com.example.homeshare.R;
import com.example.homeshare.adapters.InvitationsAdapter;
import com.example.homeshare.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private boolean firstLoad = true;
    InvitationsAdapter invitationsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.sort_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sort_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
                if (firstLoad){
                    firstLoad = false;
                    return;
                }
                showInvitationsFromFirebase(invitationsAdapter,parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (((HomeActivity)getActivity()) != null){
            ((HomeActivity)getActivity()).getDb().collection("User").document(((HomeActivity)getActivity()).getmAuth().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        RecyclerView recyclerView = view.findViewById(R.id.feedRecyclerView);
                        if (((HomeActivity)getActivity()) != null) {
                            InvitationsAdapter adapter = new InvitationsAdapter(getContext()
                                    , ((HomeActivity) getActivity()).getDb(), ((HomeActivity) getActivity()).getmAuth(), documentSnapshot.toObject(User.class));
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                            showInvitationsFromFirebase(adapter,"Most Recent Deadline");
                        }
                    }
                }
            });
        }

        return view;
    }

    public void showInvitationsFromFirebase(InvitationsAdapter adapter,String sort){
        this.invitationsAdapter = adapter;
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
                            if (!sort.equals("")){
                                sortInvitations(invitations,sort);
                            }
                            adapter.setInvitations(invitations);
                        } else {
                            Log.d("Get Invitations", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void sortInvitations(ArrayList<Invitation> invitations,String sort){
        if (sort.equals("Most Recent Deadline")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            invitations.sort((invitation1,invitation2) -> {
                Date date1 = null;
                try {
                    date1 = formatter.parse(invitation1.getDay()+"-"+invitation1.getMonth()+"-"+invitation1.getYear());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = formatter.parse(invitation2.getDay()+"-"+invitation2.getMonth()+"-"+invitation2.getYear());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.compareTo(date2);
            });
        }
        else if (sort.equals("Least Recent Deadline")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
            invitations.sort((invitation1,invitation2) -> {
                Date date1 = null;
                try {
                    date1 = formatter.parse(invitation1.getDay()+"-"+invitation1.getMonth()+"-"+invitation1.getYear());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = formatter.parse(invitation2.getDay()+"-"+invitation2.getMonth()+"-"+invitation2.getYear());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date2.compareTo(date1);
            });
        }
        else if (sort.equals("Most Expensive")){
            invitations.sort((invitation1,invitation2) -> {
                if (Integer.parseInt(invitation1.getPrice()) > Integer.parseInt(invitation2.getPrice())) return -1;
                return 1;
            });
        }
        else if (sort.equals("Least Expensive")){
            invitations.sort((invitation1,invitation2) -> {
                if (Integer.parseInt(invitation1.getPrice()) > Integer.parseInt(invitation2.getPrice())) return 1;
                return -1;
            });
        }
        else if (sort.equals("Most Spots Available")){
            invitations.sort((invitation1,invitation2) -> {
                if (invitation1.getRoommates() > invitation2.getRoommates()) return -1;
                return 1;
            });
        }
        else if (sort.equals("Least Expensive")){
            invitations.sort((invitation1,invitation2) -> {
                if (invitation1.getRoommates() > invitation2.getRoommates()) return 1;
                return -1;
            });
        }
        else if (sort.equals("Closest to USC")){
            invitations.sort(Comparator.comparing(Invitation::getLocation));
        }
        else if (sort.equals("Farthest from USC")){
            invitations.sort(Comparator.comparing(Invitation::getLocation).reversed());
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}