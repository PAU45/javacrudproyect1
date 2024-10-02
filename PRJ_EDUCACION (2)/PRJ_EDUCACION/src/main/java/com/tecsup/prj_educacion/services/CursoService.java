package com.tecsup.prj_educacion.services;

import com.tecsup.prj_educacion.modelo.entities.Curso;

import java.util.List;

public interface CursoService {

    public void grabar(Curso curso);
    public void actualizar(Curso curso);
    public void borrar(String id);
    public List<Curso> listar();
    public Curso buscar(String id);
}
