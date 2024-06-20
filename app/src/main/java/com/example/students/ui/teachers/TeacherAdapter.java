package com.example.students.ui.teachers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.R;
import com.example.students.databinding.ItemTeachBinding;
import com.example.students.model.Teacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    ItemTeachBinding binding;
    Context context;
    List<Teacher> list = new ArrayList<>(); ;
    ArrayList<Teacher> setList=new ArrayList<>();
    ArrayList<Teacher> selected_list = new ArrayList<>();
    NavController navController;

    public TeacherAdapter(Context context, List<Teacher> list) {
        this.context = context;
        this.list = list;
    }
    public TeacherAdapter() {

    }
    @NonNull
    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTeachBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Teacher> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTeachBinding binding;
        public ViewHolder(@NonNull ItemTeachBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(Teacher modelM) {
            binding.userName.setText(modelM.getName());
            binding.estimation.setText(String.valueOf(modelM.getRate()));
            binding.languageLayer.setText(modelM.getLevel());
            binding.price.setText(String.valueOf(modelM.getPrice()));

            binding.more.setOnClickListener(v -> {
                selected_list.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see more", selected_list);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.descriptionFragment, bundle);
                Log.e("TAG", "pass data ! !");
            });
            Picasso.get().load(modelM.getPhoto()).into(binding.imageTeacher);
//            binding.btnZoom.setOnClickListener(v -> {
//                selected_list.add(modelM);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("see more", selected_list);
//                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
//                navController.navigate(R.id.descriptionFragment, bundle);
//                Log.e("TAG", "pass data ! !");
//            });
//

        }
    }
}