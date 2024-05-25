package com.example.lab5_20170404_iot;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20170404_iot.databinding.ActivityRegisterBinding;
import com.example.lab5_20170404_iot.databinding.FragmentCreateTareaBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class createTaskBottomShettFragment extends AppCompatActivity {


    FragmentCreateTareaBinding binding;

    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentCreateTareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String codigoUsuario = intent.getStringExtra("codigo_usuario");

        databaseHelper = new DatabaseHelper(this);

        binding.addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String titulo = binding.addTaskTitle.getText().toString();
                String descripcion = binding.addTaskDescription.getText().toString();
                String fecha = binding.taskDate.getText().toString();
                String hora = binding.taskTime.getText().toString();


                Boolean insert = databaseHelper.insertData2(codigoUsuario,titulo, descripcion,fecha,hora);

                if (insert == true){
                    Toast.makeText(createTaskBottomShettFragment.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    //notificacion de registro exitoso... entraria aqui
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(createTaskBottomShettFragment.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }



}
