package com.example.lab5_20170404_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab5_20170404_iot.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.registerCodigo.getText().toString();
                String password = binding.registerPassword.getText().toString();
                String nombre = binding.registerNombre.getText().toString();
                String confirmPassword = binding.registerConfirm.getText().toString();

                if (codigo.equals("")|| password.equals("") || confirmPassword.equals("")||nombre.equals(""))
                    Toast.makeText(RegisterActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)){
                        Boolean checkUserCodigo = databaseHelper.checkCodigo(codigo);

                        if(checkUserCodigo == false){
                            Boolean insert = databaseHelper.insertData(codigo,password, nombre);

                            if (insert == true){
                                Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                //notificacion de registro exitoso... entraria aqui
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this, "Ya existe el usuario, porfavor inicie sesión", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}