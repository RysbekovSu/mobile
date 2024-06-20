package com.example.students.description;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.students.R;
import com.example.students.databinding.ItemDesc2Binding;
import com.example.students.databinding.ItemDescBinding;
import com.example.students.model.ArticelModel;
import com.example.students.model.Teacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DescAdapter2 extends RecyclerView.Adapter<DescAdapter2.ViewHolder> {
    ItemDesc2Binding binding;
    Context context;

    List<ArticelModel> listD = new ArrayList<>();
    public void setListD(List<ArticelModel> listD) {
        this.listD = listD;
    }

    public DescAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDesc2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

        ItemDesc2Binding binding;
        public ViewHolder(@NonNull ItemDesc2Binding itemView) {
            super(itemView.getRoot());
            binding = itemView; // Проинициализировать binding в конструкторе ViewHolder
        }

        public void onBind(ArticelModel modelM) {
            binding.author.setText(modelM.getAuthor());
            binding.articelName.setText(modelM.getName());
            binding.quote.setText(modelM.getQuote());

            binding.description.setText(modelM.getDescription());




            Picasso.get().load(modelM.getPhoto()).into(binding.imageTeacher);
        }
    }
}
