package com.tecsup.prj_educacion.modelo.entities;

import java.time.LocalDateTime;

public class Comentarios {
    private int id;
    private int calificacion;
    private String comentario;
    private LocalDateTime fecha;

    // Constructor con parámetros
    public Comentarios(int id, int calificacion, String comentario, LocalDateTime fecha) {
        this.id = id;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Constructor sin parámetros (opcional)
    public Comentarios() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}