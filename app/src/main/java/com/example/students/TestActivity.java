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
import com.example.students.databinding.ActivityTestBinding;
import com.example.students.model.TestQuestion;
import com.example.students.remote_data.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private RecyclerView rvChoose;
    private TestAdapter adapter;
    private List<TestQuestion> testQuestions;
    ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        rvChoose = findViewById(R.id.rv_tests);
        ImageView back = findViewById(R.id.back);

        testQuestions = new ArrayList<>(); // Initialize the list

        // Set up RecyclerView and Adapter
        adapter = new TestAdapter(testQuestions, this);
        rvChoose.setLayoutManager(new LinearLayoutManager(this));
        rvChoose.setAdapter(adapter);

        // Fetch test questions from API
        fetchTestQuestions();

        // Back button click listener
        back.setOnClickListener(v -> finish());
    }

    private void fetchTestQuestions() {
        Call<List<TestQuestion>> apiCall = RetrofitBuilder.getInstance().getApi().getTestQuestion();
        apiCall.enqueue(new Callback<List<TestQuestion>>() {
            @Override
            public void onResponse(Call<List<TestQuestion>> call, Response<List<TestQuestion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    testQuestions.addAll(response.body()); // Add all fetched questions to the list
                    adapter.setTestQuestions(testQuestions); // Update the adapter with the new data
                } else {
                    Toast.makeText(TestActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TestQuestion>> call, Throwable throwable) {
                Log.e("TestActivity", "Failed to fetch data: " + throwable.getLocalizedMessage());
                Toast.makeText(TestActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
