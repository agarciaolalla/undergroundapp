package com.example.undergroundapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdaptadorConsumos extends RecyclerView.Adapter<AdaptadorConsumos.MyViewHolder> {

    ArrayList<Consumo> consumos;

    public AdaptadorConsumos(ArrayList<Consumo> consumos) {
        this.consumos = consumos;
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

        TextView txvMes = holder.txvMes;
        TextView txvMb = holder.txvMb;
        TextView txvllamadas = holder.txvllamadas;
        TextView txvmensajes = holder.txvmensajes;

        String mesText = (consumos.get(position).getMes() + " Mes" + "\t\t\t" );
        String datosText = (consumos.get(position).getMb() + " Mb" + "\t\t\t" );
        String mensajesText = (consumos.get(position).getMensajes() + " Mensajes" + "\t\t\t");
        String llamadasText = (consumos.get(position).getLlamadas() + " Llamadas" + "\t\t\t" );

        txvMes.setText(consumos.get(position).getMes());
        txvMb.setText(datosText);
        txvllamadas.setText(llamadasText);
        txvmensajes.setText(mensajesText);
    }

    @Override
    public int getItemCount(){
        return consumos.size();
    }
    //se inicializan los textView para mostrarlos en el RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txvMes;
        TextView txvmensajes;
        TextView txvllamadas;
        TextView txvMb;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.txvMes = (TextView) itemView.findViewById(R.id.txvMes);
            this.txvMb = (TextView) itemView.findViewById(R.id.txvMb);
            this.txvllamadas = (TextView) itemView.findViewById(R.id.txvllamadas);
            this.txvmensajes = (TextView) itemView.findViewById(R.id.txvmensajes);
        }
        }

    }

