package com.tecsup.prj_educacion;

import com.tecsup.prj_educacion.services.ComentariosService;
import com.tecsup.prj_educacion.services.impl.ComentariosServiceImpl;
import com.tecsup.prj_educacion.util.Tipo; // Asegúrate de importar Tipo
import com.tecsup.prj_educacion.util.Util; // Asegúrate de importar Util

public class Main {
    public static void main(String[] args) {
        // Obtener el tipo desde la clase Util
        Tipo tipo = Util.opc; // Asegúrate de que Util.opc esté correctamente configurado

        // Crear la instancia de ComentariosServiceImpl con el tipo
        ComentariosService servicio = new ComentariosServiceImpl(tipo);

        // Aquí puedes usar el servicio para realizar operaciones
        // Por ejemplo, listar comentarios:
        System.out.println(servicio.listar());
    }
}