package com.example.undergroundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Fiesta extends AppCompatActivity{

    String nombre;
    String tipo;
    String fecha;
    String direccion;


    //getters y setters de todas las variables a utilizar en menu consumos

    public Fiesta() {
    }

    public Fiesta(String nombre, String tipo, String fecha,String direccion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.direccion = direccion;



    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}
