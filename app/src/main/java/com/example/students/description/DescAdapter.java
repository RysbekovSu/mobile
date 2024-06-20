package com.example.students.description;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.students.R;
import com.example.students.databinding.ItemDescBinding;
import com.example.students.model.Teacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {
    ItemDescBinding binding;
    Context context;
    String username;

    List<Teacher> listD = new ArrayList<>();
    public void setListD(List<Teacher> listD) {
        this.listD = listD;
    }

    public DescAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDescBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listD.get(position));

    }





    @Override
    public int getItemCount() {
        return listD.size();
    }



    public  class ViewHolder extends  RecyclerView.ViewHolder{

        ItemDescBinding binding;
        public ViewHolder(@NonNull ItemDescBinding itemView) {
            super(itemView.getRoot());
            binding = itemView; // Проинициализировать binding в конструкторе ViewHolder
        }

        public void onBind(Teacher modelM) {
            binding.userName.setText(modelM.getName());
            binding.estimation.setText(String.valueOf(modelM.getRate()));
            binding.languageLayer.setText(modelM.getLevel());
            binding.price.setText(String.valueOf(modelM.getPrice()));

            SharedPreferences preferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            username = preferences.getString("username", "");

            binding.buttonStatLern.setOnClickListener(v -> {
                String telegramNumber = modelM.getTel(); // Assuming 'tel' field holds the telegram phone number

                try {
                    Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
                    telegramIntent.setData(Uri.parse("tg://resolve?domain=" + telegramNumber));
                    context.startActivity(telegramIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Telegram app is not installed!", Toast.LENGTH_SHORT).show();
                }
            });

            Picasso.get().load(modelM.getPhoto()).into(binding.imageTeacher);
        }
    }
}
