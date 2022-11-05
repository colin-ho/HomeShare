package com.example.homeshare.ui.createInvitation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeshare.databinding.FragmentCreateInvitationBinding;

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}