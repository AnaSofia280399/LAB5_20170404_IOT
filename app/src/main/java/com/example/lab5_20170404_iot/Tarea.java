package com.example.lab5_20170404_iot;
import java.util.Date;

public class Tarea {
    private String codigoUsuario;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String hora;
    private boolean completada;

    // Constructor
    public Tarea(String codigoUsuario, String titulo, String descripcion, String fecha, String hora) {
        this.codigoUsuario = codigoUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.completada = false; // inicialmente la tarea no está completada
    }

    // Getters y Setters
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}

