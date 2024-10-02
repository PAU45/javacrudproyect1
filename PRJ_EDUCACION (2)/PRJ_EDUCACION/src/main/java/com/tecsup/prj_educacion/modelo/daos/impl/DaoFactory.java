package com.tecsup.prj_educacion.modelo.daos.impl;
import com.tecsup.prj_educacion.modelo.daos.UsuariosDao;
import  com.tecsup.prj_educacion.util.Util;
import com.tecsup.prj_educacion.modelo.daos.AdministradorDao;
import com.tecsup.prj_educacion.modelo.daos.CursoDao;
import com.tecsup.prj_educacion.modelo.daos.ComentariosDao; // Importa la interfaz ComentariosDao
import com.tecsup.prj_educacion.util.Tipo;

import java.sql.Connection;

public class DaoFactory {

    public static AdministradorDao getAdministradorDao(Tipo tipo) {
        switch (tipo) {
            case MEM:
                // return new AdministradorDaoMemory();
            case PST:
                // return new AdministradorDaoPreparedStatement();
            case CST:
                return new AdministradorDaoCallableStatement();
            default:
                return null;
        }
    }

    public static CursoDao getCursoDao(Tipo tipo) {
        switch (tipo) {
            case MEM:
                // return new CursoDaoMemory();
            case PST:
                // return new CursoDaoPreparedStatement();
            case CST:
                return new CursoDaoCallableStatement();
            default:
                return null;
        }
    }


    public static ComentariosDao getComentariosDao(Tipo tipo) {
        switch (tipo) {
            case MEM:
                // return new ComentariosDaoMemory();
            case PST:
                // return new ComentariosDaoPreparedStatement();
            case CST:
                return new ComentariosDaoCallableStatement();
            default:
                return null;
        }
    }
    public static UsuariosDao getUsuariosDao(Tipo tipo) {
        switch (tipo) {
            case MEM:
                // return new UsuariosDaoMemory();
            case PST:
                // return new UsuariosDaoPreparedStatement();
            case CST:
                return new UsuariosDaoCallableStatement();
            default:
                return null;
        }
    }
}

