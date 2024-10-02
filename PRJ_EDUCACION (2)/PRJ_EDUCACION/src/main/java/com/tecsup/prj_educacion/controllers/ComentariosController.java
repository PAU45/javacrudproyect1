package com.tecsup.prj_educacion.controllers;

import com.tecsup.prj_educacion.util.Util;
import com.tecsup.prj_educacion.modelo.entities.Comentarios;
import com.tecsup.prj_educacion.services.ComentariosService;
import com.tecsup.prj_educacion.services.impl.ComentariosServiceImpl;
import com.tecsup.prj_educacion.util.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ComentariosController", urlPatterns = {"/comentariosController"})
public class ComentariosController extends HttpServlet {

    private ComentariosService servicio;

    @Override
    public void init() throws ServletException {
        Tipo tipo = Util.opc;
        servicio = new ComentariosServiceImpl(tipo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Comentarios comentario = new Comentarios();

        int id = Integer.parseInt(request.getParameter("id"));
        comentario.setId(id);
        comentario.setCalificacion(Integer.parseInt(request.getParameter("txtCalificacion")));
        comentario.setComentario(request.getParameter("txtComentario"));
        comentario.setFecha(java.time.LocalDateTime.now());

        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar":
                servicio.grabar(comentario);
                break;
            case "actualizar":
                servicio.actualizar(comentario);
                break;
            case "eliminar":
                servicio.borrar(id);
                break;
            default:
                break;
        }
        response.sendRedirect("ComentariosMan.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("listar".equals(accion)) {
            listarComentarios(request, response);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            servicio.borrar(id);
            response.sendRedirect("ComentariosMan.jsp");
        } else {
            response.sendRedirect("ComentariosMan.jsp");
        }
    }

    private void listarComentarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("comentarios", servicio.listar());
        request.getRequestDispatcher("comentariosList.jsp").forward(request, response);
    }
}
