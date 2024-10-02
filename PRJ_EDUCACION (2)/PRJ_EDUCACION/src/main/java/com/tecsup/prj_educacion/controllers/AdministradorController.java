package com.tecsup.prj_educacion.controllers;

import com.tecsup.prj_educacion.modelo.entities.Administrador;
import com.tecsup.prj_educacion.services.AdministradorService;
import com.tecsup.prj_educacion.services.impl.AdministradorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdministradorController", urlPatterns = {"/sValidador", "/admin"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sUsuraio = request.getParameter("txtUsuario");
        String sPassword = request.getParameter("txtPassword");

        AdministradorService servicio = new AdministradorServiceImpl();

        Administrador adm = servicio.validar(sUsuraio, sPassword);

        if(adm!=null){
            request.getSession().setAttribute("eladministrador", adm);
            response.sendRedirect("principal.jsp");
        }else{
            response.sendRedirect("error.jsp");
        }
    }
}

