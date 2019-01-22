package com.antonio.practica_ut04_sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {

    private UserModel userModel;
    private EditText editarnombre, editaremail;
    private Button botonactualizar, botonborrar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);

        editarnombre = findViewById(R.id.edit_nombre);
        editaremail = findViewById(R.id.edit_email);
        botonactualizar = findViewById(R.id.button_update);
        botonborrar = findViewById(R.id.button_delete);

        editarnombre.setText(userModel.getNombre());
        editaremail.setText(userModel.getEmail());

        botonactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateUser(userModel.getId(),editarnombre.getText().toString(),editaremail.getText().toString());
                Toast.makeText(UpdateDeleteActivity.this, "Actualizado Correctamente!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        botonborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UpdateDeleteActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Esta acción no se puede deshacer")
                        .setMessage("¿Está seguro que quiere borrar este contacto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseHelper.deleteUSer(userModel.getId());
                                Toast.makeText(UpdateDeleteActivity.this, "Se ha borrado el contacto!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });

        /*botonborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDeleteActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });*/

    }
}
