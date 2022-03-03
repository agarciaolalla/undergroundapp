package com.example.undergroundapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuFiestas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager LM;
    private AdaptadorFiestas adaptador;
    private RecyclerView.LayoutManager layoutManager;

    String getMail;
    String getRol;
    String getName;
    String getPass;
    Button volver;

    private DatabaseReference db;

    TextView tvNombre;
    TextView tvTipo;
    TextView tvFecha;
    TextView tvDireccion;
    ImageView imageItems;

    ArrayList<Fiesta> guardarFiestas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_fiestas);

        getSupportActionBar().setTitle("Próximas Fiestas");

        db = FirebaseDatabase.getInstance().getReference();

        //Inicializo todas las variables para que no haya NullPointerException
        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";

        //inicializo boton volver, y todos los textView
        volver = (Button) findViewById(R.id.volvermenu);

        tvNombre = (TextView) findViewById(R.id.nombre);
        tvTipo = (TextView) findViewById(R.id.tipo);
        tvFecha = (TextView) findViewById(R.id.fecha);
        tvDireccion = (TextView) findViewById(R.id.tDireccion);
        imageItems=(ImageView) findViewById(R.id.imageItems);


        getMail = getIntent().getStringExtra("mail");
        getName = getIntent().getStringExtra("name");
        getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        //inicializar recyclerview
        recyclerView=(RecyclerView) findViewById(R.id.recyclerlista);

        //declarar visualizacion de los elementos
        LM = new LinearLayoutManager(this);
        LM.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(LM);

        obtenerFiestas();


        //boton volver
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFiestas.this, MenuPrincipal.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol", getRol);
                i.putExtra("pass", getPass);
                i.putExtra("name", getName);

                startActivity(i);
            }
        });



    }
    //asigno las variables al array
    public void obtenerFiestas() {


        //Método para guardar los datos del usuario introducido (ROL y MAIL)
        db.child("Fiestas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) { //Mediante un for en la snapshot lo recorremos y hacemos una simple comprobación con el mail introducido, para que te guarde los datos concretos.

                        guardarFiestas.add(new Fiesta(ds.child("nombre").getValue().toString(), ds.child("tipo").getValue().toString(), ds.child("fecha").getValue().toString(), ds.child("direccion").getValue().toString()));


                    }
                }

                adaptador = new AdaptadorFiestas(guardarFiestas);
                recyclerView.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });





    }

}