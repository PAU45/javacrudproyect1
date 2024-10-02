package com.tecsup.prj_educacion.services.impl;

import com.tecsup.prj_educacion.modelo.daos.CursoDao;
import com.tecsup.prj_educacion.modelo.daos.impl.DaoFactory;
import com.tecsup.prj_educacion.modelo.entities.Curso;
import com.tecsup.prj_educacion.services.CursoService;
import com.tecsup.prj_educacion.util.Util;

import java.util.List;

public class CursoServiceImpl implements CursoService {

    private CursoDao dao;

    public CursoServiceImpl() {
        dao= DaoFactory.getCursoDao(Util.opc);
    }

    @Override
    public void grabar(Curso curso) {
        dao.create(curso);
    }

    @Override
    public void actualizar(Curso curso) {
            dao.update(curso);
    }

    @Override
    public void borrar(String id) {
            dao.delete(id);
    }

    @Override
    public List<Curso> listar() {
        return dao.findAll();
    }

    @Override
    public Curso buscar(String id) {
        return dao.find(id);
    }
}
