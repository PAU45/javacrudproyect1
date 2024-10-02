package com.tecsup.prj_educacion.util;

import java.sql.Connection;
import java.util.ResourceBundle;

public class Util {

    public static Tipo opc;
    public static final String ERROR1;
    public static final String ERROR2;

    static {
        ResourceBundle properties = ResourceBundle.getBundle("config");
        opc = Tipo.valueOf(properties.getString("opc"));
        ERROR1 = properties.getString("error1");
        ERROR2 = properties.getString("error2");
    }

    public static final String RUTA = "c:/tecsup/Datos.ini";

}