package com.tecsup.prj_educacion.services.impl;

import com.tecsup.prj_educacion.modelo.daos.UsuariosDao;
import com.tecsup.prj_educacion.modelo.daos.impl.DaoFactory;
import com.tecsup.prj_educacion.modelo.entities.Usuarios;
import com.tecsup.prj_educacion.services.UsuariosService;
import com.tecsup.prj_educacion.util.Tipo; // Asegúrate de importar Tipo

import java.util.List;

public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosDao dao; // Declaración del DAO

    // Constructor modificado para aceptar Tipo
    public UsuariosServiceImpl(Tipo tipo) {
        dao = DaoFactory.getUsuariosDao(tipo); // Inicializa el DAO usando el tipo
    }

    @Override
    public void grabar(Usuarios usuario) {
        dao.create(usuario); // Llama al método create
    }

    @Override
    public void actualizar(Usuarios usuario) {
        dao.update(usuario); // Llama al método update
    }

    @Override
    public void borrar(int id) {
        dao.delete(id); // Llama al método delete
    }

    @Override
    public List<Usuarios> listar() {
        return dao.findAll(); // Llama al método findAll
    }

    @Override
    public Usuarios buscar(int id) {
        return dao.find(id); // Llama al método find
    }
}