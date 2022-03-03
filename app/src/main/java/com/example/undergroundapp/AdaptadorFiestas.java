package com.example.undergroundapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorFiestas extends RecyclerView.Adapter<AdaptadorFiestas.MyViewHolder> {

    ArrayList<Fiesta> fiestas;

    public AdaptadorFiestas(ArrayList<Fiesta> fiestas) {
        this.fiestas = fiestas;
    }

    @NonNull
    @Override
    //creamos la instancia del archivo xml de diseño de la vista
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_lista,parent,false);
        return new MyViewHolder(view);
    }

    //lanzamos los textView declarados, para luego rellenar los campos en la clase Menu consumos
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position){

        TextView tvNombre = holder.tvNombre;
        TextView tvTipo = holder.tvTipo;
        TextView tvFecha = holder.tvFecha;
        TextView tvDireccion = holder.tvDireccion;


        tvNombre.setText(fiestas.get(position).getNombre());
        tvTipo.setText(fiestas.get(position).getTipo());
        tvFecha.setText(fiestas.get(position).getFecha());
        tvDireccion.setText(fiestas.get(position).getDireccion());
    }

    @Override
    public int getItemCount(){
        return fiestas.size();
    }
    //se inicializan los textView para mostrarlos en el RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre;
        TextView tvTipo;
        TextView tvFecha;
        TextView tvDireccion;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.tvNombre = (TextView) itemView.findViewById(R.id.nombre);
            this.tvTipo = (TextView) itemView.findViewById(R.id.tipo);
            this.tvFecha = (TextView) itemView.findViewById(R.id.fecha);
            this.tvDireccion = (TextView) itemView.findViewById(R.id.direccion);

        }
        }

    }

