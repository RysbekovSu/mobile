package com.example.students.description;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.databinding.ActivityArDescBinding;
import com.example.students.model.ArticelModel;

import java.util.ArrayList;

public class ArDescActivity extends AppCompatActivity {
    ActivityArDescBinding binding;
    ArrayList<ArticelModel> d_list = new ArrayList<>();
    DescAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_desc);

        binding = ActivityArDescBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new DescAdapter2(ArDescActivity.this);

        // Получение данных из Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("article")) {
            d_list = bundle.getParcelableArrayList("article");
        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_LONG).show();
        }

        if (d_list != null && !d_list.isEmpty()) {
            adapter.setListD(d_list);
        } else {
            Toast.makeText(this, "No articles selected", Toast.LENGTH_SHORT).show();
        }
        binding.back.setOnClickListener(v -> finish());
        binding.rvDetailsCatalog.setAdapter(adapter);

    }
}
