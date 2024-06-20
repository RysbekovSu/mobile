package com.example.students;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.students.databinding.FragmentFeedbackBinding;
import com.example.students.model.Apply;
import com.example.students.remote_data.RetrofitBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackFragment extends Fragment {
    Apply apply;
    private FragmentFeedbackBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.registerButton.setOnClickListener(v -> {
            registerFeedback();
        });


        return root;
    }

    private void registerFeedback() {
        String name = binding.userName.getText().toString();
        String email = binding.email.getText().toString();
        String photo = binding.photo.getText().toString();
        String reason = binding.reason.getText().toString();
        String level = binding.level.getText().toString();
        String rateStr = binding.rate.getText().toString();
        String priceStr = binding.price.getText().toString();
        String description = binding.description.getText().toString();
        String insta = binding.insta.getText().toString();
        String tel = binding.tel.getText().toString();
        String num = binding.num.getText().toString();
        String category = binding.category.getText().toString(); // Replace with your category value

        // Check for empty strings before parsing to int
        int rate = rateStr.isEmpty() ? 0 : Integer.parseInt(rateStr);
        int price = priceStr.isEmpty() ? 0 : Integer.parseInt(priceStr);
        apply = new Apply(name, email, photo, reason, level, rate, price, description, insta, tel, num, category);

        Call<Apply> callCreateApply = RetrofitBuilder.getInstance().getApi().apply(apply);

        callCreateApply.enqueue(new Callback<Apply>() {
            @Override
            public void onResponse(Call<Apply> call, Response<Apply> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireActivity(), "Application submitted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    String errorBody = "";
                    try {
                        errorBody = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "Failed to submit application. Response code: " + response.code() + ", error: " + errorBody);
                    Toast.makeText(requireActivity(), "Failed to submit application", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Apply> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(requireActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release binding
    }
}
