package com.example.lab5_20170404_iot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class adapterTarea extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Tarea> dataList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public adapterTarea(Context context, List<Tarea> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarea tarea = dataList.get(position);
        holder.titulo.setText(tarea.getTitulo());
        holder.descripcion.setText(tarea.getDescripcion());
        holder.fecha.setText(dateFormat.format(tarea.getFecha()));
        holder.hora.setText(timeFormat.format(tarea.getHora()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView titulo, descripcion, fecha, hora;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.title);
        descripcion = itemView.findViewById(R.id.description);
        fecha = itemView.findViewById(R.id.date);
        hora = itemView.findViewById(R.id.time);
    }
}