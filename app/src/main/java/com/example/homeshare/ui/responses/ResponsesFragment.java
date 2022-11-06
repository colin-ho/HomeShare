package com.example.homeshare.ui.responses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeshare.databinding.FragmentResponsesBinding;

public class ResponsesFragment extends Fragment {

    private FragmentResponsesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResponsesViewModel responsesViewModel =
                new ViewModelProvider(this).get(ResponsesViewModel.class);

        binding = FragmentResponsesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textResponses;
        responsesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}