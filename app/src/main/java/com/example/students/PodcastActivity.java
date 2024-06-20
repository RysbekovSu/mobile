package com.example.students;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.students.adapter.PodcastAdapter;
import com.example.students.adapter.WordAdapter;
import com.example.students.model.Podcast;
import com.example.students.model.WordModel;
import com.example.students.remote_data.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PodcastActivity extends AppCompatActivity {
    private RecyclerView rvChoose;
    private PodcastAdapter adapter;
    private List<Podcast> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

        rvChoose = findViewById(R.id.rv_podcast);
        ImageView back = findViewById(R.id.back);

        words = new ArrayList<>(); // Initialize the list

        // Set up RecyclerView and Adapter
        adapter = new PodcastAdapter(words, this);
        rvChoose.setLayoutManager(new LinearLayoutManager(this));
        rvChoose.setAdapter(adapter);

        // Fetch test questions from API
        fetchTestQuestions();

        // Back button click listener
        back.setOnClickListener(v -> finish());
    }

    private void fetchTestQuestions() {
        Call<List<Podcast>> apiCall = RetrofitBuilder.getInstance().getApi().getPodcasts();
        apiCall.enqueue(new Callback<List<Podcast>>() {
            @Override
            public void onResponse(Call<List<Podcast>> call, Response<List<Podcast>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    words.addAll(response.body()); // Add all fetched questions to the list
                    adapter.setlist(words); // Update the adapter with the new data
                } else {
                    Toast.makeText(PodcastActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Podcast>> call, Throwable throwable) {
                Log.e("TestActivity", "Failed to fetch data: " + throwable.getLocalizedMessage());
                Toast.makeText(PodcastActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
