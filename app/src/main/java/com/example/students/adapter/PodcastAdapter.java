package com.example.students.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.R;
import com.example.students.model.Podcast;

import java.util.List;
import java.util.Locale;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.ViewHolder> {
    private List<Podcast> list;
    private Context context;
    private TextToSpeech textToSpeech;
    public PodcastAdapter(List<Podcast> list, Context context) {
        this.list = list;
        this.context = context;
        initTextToSpeech();
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(new Locale("tr_TR"));
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_podcast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Podcast word = list.get(position);
        holder.user_name.setText(word.getAuthor());
        holder.text_podcast_title.setText(word.getName());
        holder.text_podcast_description.setText(word.getQuote());
        holder.button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = word.getText();
                if (textToRead != null && !textToRead.isEmpty()) {
                    textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

        holder.button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setlist(List<Podcast> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_name, text_podcast_title, text_podcast_description;
        ImageView button_stop, button_play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button_play = itemView.findViewById(R.id.button_play);

            button_stop = itemView.findViewById(R.id.button_stop);
            user_name = itemView.findViewById(R.id.user_name);
            text_podcast_title = itemView.findViewById(R.id.text_podcast_title);
            text_podcast_description = itemView.findViewById(R.id.text_podcast_description);
        }
    }

    // Release the TextToSpeech resources when not needed
    public void releaseTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
