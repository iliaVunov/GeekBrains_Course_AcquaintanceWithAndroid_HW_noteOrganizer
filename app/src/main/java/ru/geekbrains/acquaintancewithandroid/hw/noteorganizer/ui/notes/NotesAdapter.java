package ru.geekbrains.acquaintancewithandroid.hw.noteorganizer.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.geekbrains.acquaintancewithandroid.hw.noteorganizer.R;
import ru.geekbrains.acquaintancewithandroid.hw.noteorganizer.domain.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private final ArrayList<Note> items = new ArrayList<>();
    private OnNoteClicked noteClicked;

    public void setNoteLongClicked(OnNoteLongClicked noteLongClicked) {
        this.noteLongClicked = noteLongClicked;
    }

    private OnNoteLongClicked noteLongClicked;
    private final Fragment fragment;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public void addItems(ArrayList<Note> toAdd) {
        items.addAll(toAdd);
    }

    public void clear() {
        items.clear();
    }

    // Временный метод объединяющммий функционал методов addItems и clear (улучшение для экономии места)

    //метод добавления нового элемента в коллекцию
    public void addItem(Note note) {
        items.add(note);
    }

    public void deleteItem(int position) {
        items.remove(position);
    }

    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        Note item = items.get(position);
        holder.getTitleNote().setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Note getItemAtIndex(int contextMenuItemPosition) {
        return items.get(contextMenuItemPosition);
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);
    }

    interface OnNoteLongClicked {
        void onNoteLongClicked(View itemView, int position,  Note note);
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleNote;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleNote = itemView.findViewById(R.id.textView);
            fragment.registerForContextMenu(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noteClicked != null) {
                        noteClicked.onNoteClicked(items.get(getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(noteLongClicked != null) {
                        noteLongClicked.onNoteLongClicked(itemView, getAdapterPosition(), items.get(getAdapterPosition()));
                    }
                    return false;
                }
            });
        }
        public TextView getTitleNote() {
            return titleNote;
        }
    }
}