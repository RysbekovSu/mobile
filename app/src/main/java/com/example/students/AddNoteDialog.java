package com.example.students;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.students.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteDialog extends DialogFragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button addButton;

    private long selectedDateMillis;

    public void setSelectedDate(long selectedDateMillis) {
        this.selectedDateMillis = selectedDateMillis;
    }

    public interface AddNoteDialogListener {
        void onNoteAdded(Note note);
    }

    private AddNoteDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddNoteDialogListener) getTargetFragment(); // Use getTargetFragment() instead of context
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString() + " must implement AddNoteDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_note, null);

        titleEditText = view.findViewById(R.id.edit_text_title);
        descriptionEditText = view.findViewById(R.id.edit_text_description);
        addButton = view.findViewById(R.id.button_add);

        addButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String date = SimpleDateFormat.getDateInstance().format(new Date(selectedDateMillis));

            if (!TextUtils.isEmpty(title)) {
                Note newNote = new Note(title, description, date, selectedDateMillis);
                listener.onNoteAdded(newNote);
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please enter a title", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view)
                .setTitle("Add Note")
                .setNegativeButton("Cancel", (dialog, which) -> dismiss());

        return builder.create();
    }
}
