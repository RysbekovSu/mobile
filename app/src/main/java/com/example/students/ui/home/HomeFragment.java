package com.example.students.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.AddNoteDialog;
import com.example.students.R;
import com.example.students.adapter.NotesAdapter;
import com.example.students.databinding.FragmentHomeBinding;
import com.example.students.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment implements AddNoteDialog.AddNoteDialogListener {

    private CalendarView calendarView;
    private RecyclerView notesRecyclerView;
    private TextView noNotesText;
    private FloatingActionButton fabAddNote;

    private List<Note> noteList;
    private NotesAdapter notesAdapter;

    private long selectedDateMillis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        calendarView = view.findViewById(R.id.calendar_view);
        notesRecyclerView = view.findViewById(R.id.notes_recycler_view);
        noNotesText = view.findViewById(R.id.no_notes_text);
        fabAddNote = view.findViewById(R.id.fab_add_note);

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(new ArrayList<>(), new NotesAdapter.OnNoteRemoveListener() {
            @Override
            public void onNoteRemove(Note note) {
                removeNote(note);
            }
        });
        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth, 0, 0, 0);
            selectedDateMillis = calendar.getTimeInMillis();
            updateNotesForSelectedDate();
        });

        fabAddNote.setOnClickListener(v -> showAddNoteDialog());

        // Initialize with the current date
        selectedDateMillis = calendarView.getDate();
        updateNotesForSelectedDate();

        return view;
    }

    private void showAddNoteDialog() {
        AddNoteDialog dialog = new AddNoteDialog();
        dialog.setSelectedDate(selectedDateMillis);
        dialog.setTargetFragment(this, 0);
        dialog.show(getParentFragmentManager(), "AddNoteDialog");
    }

    @Override
    public void onNoteAdded(Note note) {
        noteList.add(note);
        updateNotesForSelectedDate();  // Ensure the added note is shown for the selected date
    }

    private void removeNote(Note note) {
        noteList.remove(note);
        updateNotesForSelectedDate();  // Ensure the removed note is updated for the selected date
    }

    private void updateNotesForSelectedDate() {
        List<Note> filteredNotes = new ArrayList<>();
        for (Note note : noteList) {
            if (isSameDay(note.getDateMillis(), selectedDateMillis)) {
                filteredNotes.add(note);
            }
        }

        notesAdapter.setNotes(filteredNotes);
        updateNoNotesText(filteredNotes.size());
    }

    private boolean isSameDay(long millis1, long millis2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(millis1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(millis2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    private void updateNoNotesText(int noteCount) {
        if (noteCount == 0) {
            noNotesText.setVisibility(View.VISIBLE);
        } else {
            noNotesText.setVisibility(View.GONE);
        }
    }
}
