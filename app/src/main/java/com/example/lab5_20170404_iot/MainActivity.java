package com.example.lab5_20170404_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_20170404_iot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private RecyclerView taskRecycler;
    private ImageView noDataImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTaskButton = findViewById(R.id.addTask);
        taskRecycler = findViewById(R.id.taskRecycler);
        noDataImage = findViewById(R.id.noDataImage);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        taskRecycler.setLayoutManager(gridLayoutManager);

        // Configura el RecyclerView con un LinearLayoutManager y un adapter

        // Manejo del botón de añadir tarea
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar actividad para añadir nueva tarea
                Intent intent = new Intent(MainActivity.this, createTaskBottomShettFragment.class);
                startActivity(intent);
            }
        });

        // Cargar tareas existentes
        loadTasks();
    }

    private void loadTasks() {
        // Cargar la lista de tareas de la base de datos
        // Si no hay tareas, mostrar 'noDataImage', de lo contrario configurar y mostrar el RecyclerView
        boolean hasTasks = false; // Determinar si hay tareas
        if (hasTasks) {
            noDataImage.setVisibility(View.GONE);
            taskRecycler.setVisibility(View.VISIBLE);
            // configurar el adapter del RecyclerView
        } else {
            noDataImage.setVisibility(View.VISIBLE);
            taskRecycler.setVisibility(View.GONE);
        }
    }
}