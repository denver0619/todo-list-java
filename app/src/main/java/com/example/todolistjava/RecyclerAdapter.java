package com.example.todolistjava;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Task> taskList;

//    To gain access to recylerview adapter
    private  RecyclerView recyclerView;


    public RecyclerAdapter(ArrayList<Task> taskList, RecyclerView recyclerView) {
//        list of tasks and the recyclerview is required
        this.taskList = taskList;
        this.recyclerView = recyclerView;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private EditText editText;
        private ImageButton done;
        private ImageButton edit;
        private ImageButton remove;
        boolean doneState = false;
        boolean editState = false;
        public MyViewHolder(final View itemView) {
            super(itemView);
//            finds the elements from the layout (task_tile.xml) that will be inserted and has function
            editText = itemView.findViewById(R.id.text_field);
            done = itemView.findViewById(R.id.done);
            edit = itemView.findViewById(R.id.edit);
            remove = itemView.findViewById(R.id.remove);
        }
    }

//    this contains the layout (task_tile.xml) that will be inserted in the RecyclerView
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_tile, parent, false);
        return new MyViewHolder(itemView);
    }

//    this is for managing individual layout (task_tile.xml) to access their elements on specific position from the list in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        String value = taskList.get(position).getValue();
        holder.editText.setText(value);

        //    Tile Button Listeners
        View.OnClickListener doneListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.doneState){
                    holder.done.setImageResource(R.drawable.check_box);
                    holder.doneState = true;
                } else {
                    holder.done.setImageResource(R.drawable.check_box_outline_blank);
                    holder.doneState = false;
                }
                Log.i("Current State", ((Boolean) holder.doneState).toString());
                Log.i("Value at index " + holder.getAdapterPosition(), ((Boolean) holder.doneState).toString());
            }
        };

        View.OnClickListener editListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.editState) {
                    holder.editText.setClickable(true);
                    holder.editText.setCursorVisible(true);
                    holder.editText.setFocusable(true);
                    holder.editText.setFocusableInTouchMode(true);
                    holder.editText.requestFocus();
                    holder.editState = true;
                } else {
                    holder.editText.setClickable(false);
                    holder.editText.setCursorVisible(false);
                    holder.editText.setFocusable(false);
                    holder.editText.setFocusableInTouchMode(false);
                    holder.editState = false;
                }
            }
        };

        View.OnClickListener removeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.remove(holder.getAdapterPosition());
                recyclerView.getAdapter().notifyItemRemoved(holder.getAdapterPosition());
            }
        };

        holder.done.setOnClickListener(doneListener);
        holder.edit.setOnClickListener(editListener);
        holder.remove.setOnClickListener(removeListener);
    }

//    returns the current size if the list
    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
