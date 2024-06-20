package com.example.students.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.R;
import com.example.students.model.TestQuestion;
import com.example.students.model.WordModel;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<WordModel> list;
    private Context context;

    public WordAdapter(List<WordModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_words, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WordModel word = list.get(position);
        holder.user_name.setText(word.getAuthor());
        holder.text_word_title.setText(word.getWord());
        holder.text_word_description.setText(word.getTranslation());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setlist(List<WordModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_name, text_word_title, text_word_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            text_word_title = itemView.findViewById(R.id.text_word_description);
            text_word_description = itemView.findViewById(R.id.text_word_description);
        }
    }
}
