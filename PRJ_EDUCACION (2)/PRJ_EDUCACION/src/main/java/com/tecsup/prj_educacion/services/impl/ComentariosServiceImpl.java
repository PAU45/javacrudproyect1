package com.tecsup.prj_educacion.services.impl;

import com.tecsup.prj_educacion.modelo.daos.ComentariosDao;
import com.tecsup.prj_educacion.modelo.daos.impl.DaoFactory;
import com.tecsup.prj_educacion.modelo.entities.Comentarios;
import com.tecsup.prj_educacion.services.ComentariosService;
import com.tecsup.prj_educacion.util.Tipo; // Asegúrate de importar Tipo

import java.util.List;

public class ComentariosServiceImpl implements ComentariosService {

    private final ComentariosDao dao;

    // Constructor modificado para aceptar Tipo
    public ComentariosServiceImpl(Tipo tipo) {
        dao = DaoFactory.getComentariosDao(tipo); // Pasa el tipo al método
    }

    @Override
    public List<Comentarios> listar() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            System.err.println("Error al listar comentarios: " + e.getMessage());
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    @Override
    public void grabar(Comentarios comentario) {
        try {
            dao.create(comentario);
        } catch (Exception e) {
            System.err.println("Error al grabar comentario: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Comentarios comentario) {
        try {
            dao.update(comentario);
        } catch (Exception e) {
            System.err.println("Error al actualizar comentario: " + e.getMessage());
        }
    }

    @Override
    public void borrar(Integer id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            System.err.println("Error al borrar comentario: " + e.getMessage());
        }
    }

    @Override
    public Comentarios buscar(Integer id) {
        try {
            return dao.find(id);
        } catch (Exception e) {
            System.err.println("Error al buscar comentario: " + e.getMessage());
            return null; // Retorna null en caso de error
        }
    }
}