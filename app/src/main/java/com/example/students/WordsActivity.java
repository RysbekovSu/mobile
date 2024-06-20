package com.example.students;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.adapter.TestAdapter;
import com.example.students.adapter.WordAdapter;
import com.example.students.model.TestQuestion;
import com.example.students.model.WordModel;
import com.example.students.remote_data.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordsActivity extends AppCompatActivity {
    private RecyclerView rvChoose;
    private WordAdapter adapter;
    private List<WordModel> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        rvChoose = findViewById(R.id.rv_words);
        ImageView back = findViewById(R.id.back);

        words = new ArrayList<>(); // Initialize the list

        // Set up RecyclerView and Adapter
        adapter = new WordAdapter(words, this);
        rvChoose.setLayoutManager(new LinearLayoutManager(this));
        rvChoose.setAdapter(adapter);

        // Fetch test questions from API
        fetchTestQuestions();

        // Back button click listener
        back.setOnClickListener(v -> finish());
    }

    private void fetchTestQuestions() {
        Call<List<WordModel>> apiCall = RetrofitBuilder.getInstance().getApi().getWords();
        apiCall.enqueue(new Callback<List<WordModel>>() {
            @Override
            public void onResponse(Call<List<WordModel>> call, Response<List<WordModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    words.addAll(response.body()); // Add all fetched questions to the list
                    adapter.setlist(words); // Update the adapter with the new data
                } else {
                    Toast.makeText(WordsActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WordModel>> call, Throwable throwable) {
                Log.e("TestActivity", "Failed to fetch data: " + throwable.getLocalizedMessage());
                Toast.makeText(WordsActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
