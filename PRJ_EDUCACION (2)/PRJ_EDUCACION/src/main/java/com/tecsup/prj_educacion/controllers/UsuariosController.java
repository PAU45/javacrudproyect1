package com.tecsup.prj_educacion.controllers;
import com.tecsup.prj_educacion.util.Tipo;
import com.tecsup.prj_educacion.util.Util;
import com.tecsup.prj_educacion.modelo.entities.Usuarios; // Ensure this class exists
import com.tecsup.prj_educacion.services.UsuariosService; // Ensure this interface exists
import com.tecsup.prj_educacion.services.impl.UsuariosServiceImpl; // Ensure this class exists
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsuariosController", urlPatterns = {"/usuariosController"})
public class UsuariosController extends HttpServlet {

    private UsuariosService servicio;

    @Override
    public void init() throws ServletException {
        Tipo tipo = Util.opc; // Assuming Util has an opc field for the service type
        servicio = new UsuariosServiceImpl(tipo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuarios usuario = new Usuarios();

        // Validate and parse inputs safely
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            usuario.setId(Integer.parseInt(idParam));
        }

        usuario.setNombre(request.getParameter("txtNombre")); // Assuming this field exists
        usuario.setEmail(request.getParameter("txtEmail"));   // Assuming this field exists
        // Set other fields as necessary

        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar":
                servicio.grabar(usuario);
                break;
            case "actualizar":
                servicio.actualizar(usuario);
                break;
            case "eliminar":
                servicio.borrar(usuario.getId());
                break;
            default:
                break;
        }
        response.sendRedirect("UsuariosMan.jsp"); // Redirect to user management page
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("listar".equals(accion)) {
            listarUsuarios(request, response);
        } else if ("eliminar".equals(accion)) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                servicio.borrar(id);
            }
            response.sendRedirect("UsuariosMan.jsp");
        } else {
            response.sendRedirect("UsuariosMan.jsp");
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("usuarios", servicio.listar());
        request.getRequestDispatcher("usuariosList.jsp").forward(request, response); // JSP to list users
    }
}
