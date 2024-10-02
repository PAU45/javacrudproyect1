package com.tecsup.prj_educacion.modelo.daos;

import com.tecsup.prj_educacion.modelo.entities.Administrador;

public interface AdministradorDao {

    public Administrador validar(String user, String password);

}

