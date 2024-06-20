package com.example.students.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.R;
import com.example.students.model.Note;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnNoteRemoveListener onNoteRemoveListener;

    public NotesAdapter(List<Note> noteList, OnNoteRemoveListener onNoteRemoveListener) {
        this.noteList = noteList;
        this.onNoteRemoveListener = onNoteRemoveListener;
    }

    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.descriptionTextView.setText(note.getDescription());
        holder.dateTextView.setText(note.getDate());

        holder.removeButton.setOnClickListener(v -> {
            if (onNoteRemoveListener != null) {
                onNoteRemoveListener.onNoteRemove(note); // Pass the Note object
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;
        TextView dateTextView;
        ImageButton removeButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            removeButton = itemView.findViewById(R.id.button_remove);
        }
    }

    public interface OnNoteRemoveListener {
        void onNoteRemove(Note note); // Listener now expects a Note object
    }
}
