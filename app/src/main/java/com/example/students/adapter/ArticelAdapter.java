package com.example.students.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.ArticelActivity;
import com.example.students.R;
import com.example.students.databinding.ItemArticelBinding;
import com.example.students.databinding.ItemTeachBinding;
import com.example.students.description.ArDescActivity;
import com.example.students.model.ArticelModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticelAdapter extends RecyclerView.Adapter<ArticelAdapter.ViewHolder> {

    ItemArticelBinding binding;
    Context context;

    List<ArticelModel> list = new ArrayList<>(); ;
    ArrayList<ArticelModel> setList=new ArrayList<>();
    ArrayList<ArticelModel> selected_list = new ArrayList<>();

    public ArticelAdapter(Context context, List<ArticelModel> list) {
        this.context = context;
        this.list = list;
    }
    public ArticelAdapter(Context context) {
        this.context = context;
        this.list = list;
    }
    public ArticelAdapter() {

    }
    @NonNull
    @Override
    public ArticelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemArticelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticelAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<ArticelModel> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticelBinding binding;
        public ViewHolder(@NonNull ItemArticelBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(ArticelModel modelM) {
            binding.userName.setText(modelM.getAuthor());
            binding.articelName.setText(modelM.getName());
            binding.articelDescription.setText(modelM.getQuote());

            binding.moreArticel.setOnClickListener(v -> {
                selected_list.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("article", selected_list);
                Intent intent = new Intent(context, ArDescActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });




            Picasso.get().load(modelM.getPhoto()).into(binding.imageArticel);
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
