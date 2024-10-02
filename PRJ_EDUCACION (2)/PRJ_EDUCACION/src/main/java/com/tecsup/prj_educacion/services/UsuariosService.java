package com.tecsup.prj_educacion.services;

import com.tecsup.prj_educacion.modelo.entities.Usuarios;

import java.util.List;

public interface UsuariosService {
    void grabar(Usuarios usuario);
    void actualizar(Usuarios usuario);
    void borrar(int id); // Este método debe estar aquí
    List<Usuarios> listar();
    Usuarios buscar(int id); // Asegúrate de que esto también sea int
}