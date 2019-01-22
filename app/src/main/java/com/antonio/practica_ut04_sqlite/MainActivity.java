package com.antonio.practica_ut04_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonGuardar, botonVer;
    private EditText editnombre, editemail;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        botonGuardar = findViewById(R.id.button_guardar);
        botonVer = findViewById(R.id.buton_ver);
        editnombre = findViewById(R.id.edit_name);
        editemail = findViewById(R.id.edit_email);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addUserDetail(editnombre.getText().toString(), editemail.getText().toString());
                editnombre.setText("");
                editemail.setText("");
                Toast.makeText(MainActivity.this, "Guardado correctamente!", Toast.LENGTH_SHORT).show();
            }
        });

        botonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetAllUsersActivity.class);
                startActivity(intent);
            }
        });

    }
}
