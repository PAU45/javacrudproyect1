package com.tecsup.prj_educacion.modelo.entities;

import java.time.LocalDate;

public class Usuarios {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;

    // Constructor por defecto
    public Usuarios() {
    }

    // Constructor con parámetros
    public Usuarios(int id, String nombre, String correo, String contrasena, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public void setEmail(String txtEmail) {
    }
}
