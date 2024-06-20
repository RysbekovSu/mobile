package com.example.students;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.students.adapter.ArticelAdapter;
import com.example.students.databinding.ActivityArticelBinding;
import com.example.students.model.ArticelModel;
import com.example.students.model.Teacher;
import com.example.students.remote_data.RetrofitBuilder;
import com.example.students.ui.teachers.TeacherAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticelActivity extends AppCompatActivity {
    ActivityArticelBinding binding;
    private RecyclerView recyclerView;
    private ArticelAdapter adapter;
    private List<ArticelModel> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticelBinding.inflate(getLayoutInflater()); // Initialize the binding
        setContentView(binding.getRoot()); // Set the root view of the bindin

        // g
        adapter = new ArticelAdapter(ArticelActivity.this);
        binding.rvArticles.setAdapter(adapter);
        // Инициализация RecyclerView

        Call<List<ArticelModel>> apiCall = RetrofitBuilder.getInstance().getApi().getArticels();
        apiCall.enqueue(new Callback<List<ArticelModel>>() {
            @Override
            public void onResponse(Call<List<ArticelModel>> call, Response<List<ArticelModel>> response) {
                if (response.body() != null) {
                    articleList = response.body(); // Сохранение всех учителей
                    adapter.setList(articleList); // Показ всех учителей по умолчанию
                } else {
                    Toast.makeText(ArticelActivity.this, "No ability to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ArticelModel>> call, Throwable throwable) {
                Log.e("TAG", "NO DATA" + throwable.getLocalizedMessage());
                Toast.makeText(ArticelActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
            }
        });

        binding.back.setOnClickListener(v -> finish());

    }
}
