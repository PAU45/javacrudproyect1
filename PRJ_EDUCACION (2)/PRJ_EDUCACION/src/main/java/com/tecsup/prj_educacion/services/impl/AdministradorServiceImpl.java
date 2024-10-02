package com.tecsup.prj_educacion.services.impl;

import com.tecsup.prj_educacion.modelo.daos.AdministradorDao;
import com.tecsup.prj_educacion.modelo.daos.impl.DaoFactory;
import com.tecsup.prj_educacion.modelo.entities.Administrador;
import com.tecsup.prj_educacion.services.AdministradorService;
import com.tecsup.prj_educacion.util.Util;

public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorDao dao;

    public AdministradorServiceImpl() {
        dao= DaoFactory.getAdministradorDao(Util.opc);
    }


    @Override
    public Administrador validar(String u, String p) {
        return dao.validar(u, p);
    }
}
