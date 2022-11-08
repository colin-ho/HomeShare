package com.example.homeshare.ui.responses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeshare.HomeActivity;
import com.example.homeshare.Model.Invitation;
import com.example.homeshare.Model.Response;
import com.example.homeshare.R;
import com.example.homeshare.adapters.InvitationsAdapter;
import com.example.homeshare.adapters.ResponsesAdapter;
import com.example.homeshare.databinding.FragmentResponsesBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResponsesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_responses, container, false);

        RecyclerView responsesToMeRecyclerView = view.findViewById(R.id.responsesRecyclerView);
        ResponsesAdapter responsesToMeAdapter = new ResponsesAdapter(getContext(),((HomeActivity)getActivity()).getmAuth(),((HomeActivity)getActivity()).getDb());
        responsesToMeRecyclerView.setAdapter(responsesToMeAdapter);
        responsesToMeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        showResponsesFromFirebase(responsesToMeAdapter);

        return view;
    }

    public void showResponsesFromFirebase(ResponsesAdapter adapter){
        ArrayList<Response> responses = new ArrayList<>();

        ((HomeActivity)getActivity()).getDb().collection("Response").whereEqualTo("invitationCreatorUserID",
                        ((HomeActivity)getActivity()).getmAuth().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Get Responses to Me", document.getId() + " => " + document.getData());
                                Response response = document.toObject(Response.class);
                                responses.add(response);
                                response.setResponseID(document.getId());
                            }
                            showResponsesFromFirebaseToOthers(adapter,responses);
                        } else {
                            Log.d("Get Responses to Me", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void showResponsesFromFirebaseToOthers(ResponsesAdapter adapter,ArrayList<Response> responses){
        ((HomeActivity)getActivity()).getDb().collection("Response").whereEqualTo("responderUserID",
                        ((HomeActivity)getActivity()).getmAuth().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Get Responses to Others", document.getId() + " => " + document.getData());
                                Response response = document.toObject(Response.class);
                                responses.add(response);
                                response.setResponseID(document.getId());
                            }
                            adapter.setResponses(responses);
                        } else {
                            Log.d("Get Responses to Others", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}