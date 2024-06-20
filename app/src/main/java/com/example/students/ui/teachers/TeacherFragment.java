package com.example.students.ui.teachers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.students.databinding.FragmentTeachersBinding;
import com.example.students.model.Teacher;
import com.example.students.remote_data.RetrofitBuilder;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherFragment extends Fragment {

    private FragmentTeachersBinding binding;
    private TeacherAdapter adapter;
    private List<Teacher> allTeachers; // Добавлено для хранения всех учителей

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTeachersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new TeacherAdapter();
        binding.rvTeachers.setAdapter(adapter);

        // Получение данных
        fetchTeachers();

        // Установка слушателей для кнопок
        binding.child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTeachersByCategory("Children");
            }
        });

        binding.exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTeachersByCategory("Exam"); // Пример: категория 2 для экзаменов
            }
        });

        binding.conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTeachersByCategory("Conversational"); // Пример: категория 3 для разговорного
            }
        });

        return root;
    }

    private void fetchTeachers() {
        Call<List<Teacher>> apiCall = RetrofitBuilder.getInstance().getApi().getTeacher();
        apiCall.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (response.body() != null) {
                    allTeachers = response.body(); // Сохранение всех учителей
                    adapter.setList(allTeachers); // Показ всех учителей по умолчанию
                } else {
                    Toast.makeText(requireActivity(), "No ability to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable throwable) {
                Log.e("TAG", "NO DATA" + throwable.getLocalizedMessage());
                Toast.makeText(requireActivity(), "NO DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterTeachersByCategory(String category) {
        if (allTeachers != null) {
            List<Teacher> filteredList = allTeachers.stream()
                    .filter(teacher -> category.equals(teacher.getCategoryId()))  // Using .equals() for string comparison
                    .collect(Collectors.toList());
            adapter.setList(filteredList);  // Update the adapter with the filtered list
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
