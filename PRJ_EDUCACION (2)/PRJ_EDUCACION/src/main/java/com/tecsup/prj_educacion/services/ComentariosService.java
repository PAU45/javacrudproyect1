package com.tecsup.prj_educacion.services;

import com.tecsup.prj_educacion.modelo.entities.Comentarios;
import java.util.List;

public interface ComentariosService {
    List<Comentarios> listar(); // Método para listar todos los comentarios

    void grabar(Comentarios comentario); // Método para crear un nuevo comentario

    void actualizar(Comentarios comentario); // Método para actualizar un comentario existente

    void borrar(Integer id); // Método para eliminar un comentario por ID

    Comentarios buscar(Integer id); // Método para buscar un comentario por ID
}