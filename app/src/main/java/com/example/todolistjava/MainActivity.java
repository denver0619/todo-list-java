package com.example.todolistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Task> taskList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        finds the recycler view to be targeted
        recyclerView = findViewById(R.id.task_list);
//        a list that contains the tasks
        taskList = new ArrayList<>();
//        adds an adapter to RecyclerView
        setAdapter();

//        adds onclick listener to add button in main view
        View.OnClickListener addButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler h = new Handler();

                taskList.add(new Task("Task" + ((Integer)taskList.size()+1)));
                notifyDataChanged();
                Log.i("Add Button", "Clicked");
            }
        };

//        finds the image button then adds an onclick listener to it
        ImageButton button = findViewById(R.id.add_task);
        button.setOnClickListener(addButton);
    }

//    tells the adapter that the list has been changed to re render the new list into the screen
    private void notifyDataChanged() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

//    adds an adapter to the recyclerview to display and manage the list
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(taskList, recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}