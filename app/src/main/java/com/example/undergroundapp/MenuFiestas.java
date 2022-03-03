package com.example.undergroundapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MenuFiestas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager LM;
    private AdaptadorFiestas adaptador;
    private RecyclerView.LayoutManager layoutManager;

    String getMail;
    String getRol;
    String getName;
    String getPass;


    private DatabaseReference db;

    TextView tvNombre;
    TextView tvTipo;
    TextView tvFecha;
    TextView tvDireccion;
    ImageView imageItems;
    String date;

    String fechaActual;
    Date dateObject;
    Date fechaDate;

    ArrayList<Fiesta> guardarFiestas = new ArrayList<>();
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_fiestas);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual = sdf.format(c.getTime());






        getSupportActionBar().setTitle("Próximas Fiestas");

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        //Inicializo todas las variables para que no haya NullPointerException
        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";


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
    }
    //asigno las variables al array
    public void obtenerFiestas() {
        //Método para guardar los datos del usuario introducido (ROL y MAIL)
        db.child("Fiestas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) { //Mediante un for en la snapshot lo recorremos y hacemos una simple comprobación con el mail introducido, para que te guarde los datos concretos
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.

                        //Mediante lo siguiente estoy cogiendo el día de la fiesta y lo almaceno en la variable date
                        try{
                            String dob_var=(ds.child("fecha").getValue().toString());
                            dateObject = formatter.parse(dob_var);
                            date = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
                            fechaDate = formatter.parse(fechaActual);
                        }
                        catch (java.text.ParseException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Log.i("Fecha mal introducida", e.toString());
                        }

                        if(dateObject.after(fechaDate)){
                            guardarFiestas.add(new Fiesta(ds.child("nombre").getValue().toString(), ds.child("tipo").getValue().toString(), ds.child("fecha").getValue().toString(), ds.child("direccion").getValue().toString()));
                        }



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.añadir) {
            if(getRol.equals("avanzado")){
                Intent i = new Intent(MenuFiestas.this, newFiesta.class);
                i.putExtra("mail", getMail);//Te mete la variable del Mail para que en la otra clase la obtenga directamente
                i.putExtra("rol", getRol);
                i.putExtra("pass", getPass);
                i.putExtra("name", getName);
                startActivity(i);
                finish();
            }
            else{
                Toast.makeText(MenuFiestas.this,"Sólo las empresas pueden añadir nuevas fiestas",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (id == R.id.CerrarSesion) {
            mAuth.signOut();
            startActivity(new Intent(MenuFiestas.this, IniciarSesion.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}