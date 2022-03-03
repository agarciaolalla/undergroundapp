package com.example.undergroundapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class newFiesta extends AppCompatActivity {

    private EditText nombre;
    private EditText tipo;
    private EditText fecha;
    private EditText direccion;
    private Button newFiesta;
    private Button btVolver;
    String date;
    String saveNombre;
    String saveTipo;
    String saveDireccion;

    String getMail;
    String getRol;
    String getName;
    String getPass;


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_fiesta);

        getMail = getIntent().getStringExtra("mail");
        getName = getIntent().getStringExtra("name");
        getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        //Inicializamos las variables con el xml
        nombre = (EditText) findViewById(R.id.nombre);
        tipo = (EditText) findViewById(R.id.tipo);
        fecha = (EditText) findViewById(R.id.fecha);
        direccion = (EditText) findViewById(R.id.tDireccion);
        btVolver = (Button) findViewById(R.id.volver);

        newFiesta = (Button) findViewById(R.id.newFiesta);
        date ="";
        saveNombre  = "";
        saveTipo = "";
        saveDireccion = "";

        //Instanciamos Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.
        newFiesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNombre = nombre.getText().toString();
                saveTipo = tipo.getText().toString();
                saveDireccion = direccion.getText().toString();
                Date dateObject;
                //Mediante lo siguiente estoy cogiendo el día de la fiesta y lo almaceno en la variable date
                try{
                    String dob_var=(fecha.getText().toString());

                    dateObject = formatter.parse(dob_var);

                    date = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
                }

                catch (java.text.ParseException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("Fecha mal introducida", e.toString());
                }

                //Introducimos los datos en Firebase.
                Map<String, Object> map = new HashMap<>();
                map.put("nombre", saveNombre);
                map.put("tipo", saveTipo);
                map.put("fecha", date);
                map.put("direccion", saveDireccion);

                mDatabase.child("Fiestas").push().setValue(map);

            }
        });

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(newFiesta.this, MenuPrincipal.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol", getRol);
                i.putExtra("pass", getPass);
                i.putExtra("name", getName);
                startActivity(i);

            }
        });




    }
}
