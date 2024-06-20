package com.example.students.ui.facts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.students.ArticelActivity;
import com.example.students.PodcastActivity;
import com.example.students.R;
import com.example.students.TestActivity;
import com.example.students.WordsActivity;
import com.example.students.databinding.FragmentPeopleBinding;

public class PeopleFragment extends Fragment {

    private FragmentPeopleBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeopleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.test.setBackgroundResource(R.drawable.round_back_stroke_white);

                binding.podcast.setBackgroundResource(R.drawable.round_back_white10);
                binding.articel.setBackgroundResource(R.drawable.round_back_white10);
                binding.words.setBackgroundResource(R.drawable.round_back_white10);

                Intent intent = new Intent(requireContext(), TestActivity.class);
                requireActivity().startActivity(intent);


            }
        });
        binding.words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.words.setBackgroundResource(R.drawable.round_back_stroke_white);

                binding.test.setBackgroundResource(R.drawable.round_back_white10);
                binding.articel.setBackgroundResource(R.drawable.round_back_white10);
                binding.podcast.setBackgroundResource(R.drawable.round_back_white10);

                Intent intent = new Intent(requireContext(), WordsActivity.class);
//
                requireActivity().startActivity(intent);

            }
        });
        binding.podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.podcast.setBackgroundResource(R.drawable.round_back_stroke_white);

                binding.test.setBackgroundResource(R.drawable.round_back_white10);
                binding.articel.setBackgroundResource(R.drawable.round_back_white10);
                binding.words.setBackgroundResource(R.drawable.round_back_white10);


                Intent intent = new Intent(requireActivity(), PodcastActivity.class);
                startActivity(intent);

            }
        });
        binding.articel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.articel.setBackgroundResource(R.drawable.round_back_stroke_white);

                binding.test.setBackgroundResource(R.drawable.round_back_white10);
                binding.words.setBackgroundResource(R.drawable.round_back_white10);
                binding.podcast.setBackgroundResource(R.drawable.round_back_white10);


                Intent intent = new Intent(requireContext(), ArticelActivity.class);
                requireActivity().startActivity(intent);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding when the view is destroyed to avoid memory leaks
    }
}