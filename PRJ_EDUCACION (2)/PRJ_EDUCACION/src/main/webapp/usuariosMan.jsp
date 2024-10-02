<%@ page import="java.util.List" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Administrador" %>
<%@ page import="com.tecsup.prj_educacion.services.UsuariosService" %>
<%@ page import="com.tecsup.prj_educacion.services.impl.UsuariosServiceImpl" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Usuarios" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.tecsup.prj_educacion.util.Tipo" %>
<%@ page import="com.tecsup.prj_educacion.util.Util" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if (misesion.getAttribute("eladministrador") == null) {
        response.sendRedirect("error.jsp");
    } else {
        Administrador adm = (Administrador) misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();

        // Obtener el tipo desde Util
        Tipo tipo = Util.opc; // Asegúrate de que Util.opc esté correctamente configurado
        UsuariosService usuariosServicio = new UsuariosServiceImpl(tipo); // Inicializar el servicio de usuarios
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Usuarios! <%= nombre %></title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Incluye tus estilos aquí -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <!-- Para los íconos -->
</head>
<body>

<div class="container" style="margin-top:70px;">
    <div class="row mt-5">
        <jsp:include page="master.jsp" />

        <h1>Mantenimiento de Usuarios!</h1>
        <div style="padding: 10px;">
            <button class="btn btn-danger">
                <a class="nav-link link-light" aria-current="page" href="UsuarioForm.jsp">Nuevo Usuario</a>
            </button>
        </div>

        <table class="table table-dark table-hover">
            <thead>
            <tr align="center">
                <th>ID</th>
                <th>NOMBRE</th>
                <th>CORREO</th>
                <th>TELEFONO</th>
                <th>DIRECCIÓN</th>
                <th>ACCIONES</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    List<Usuarios> usuarios = usuariosServicio.listar();
                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuarios usuario : usuarios) {
            %>
            <tr align="center">
                <td><%= usuario.getId() %></td>
                <td><%= usuario.getNombre() %></td>
                <td><%= usuario.getCorreo() %></td>
                <td><%= usuario.getTelefono() != null ? usuario.getTelefono() : "N/A" %></td>
                <td><%= usuario.getDireccion() != null ? usuario.getDireccion() : "N/A" %></td>
                <td>
                    <a class="btn btn-warning" href="usuariosController?accion=eliminar&id=<%= usuario.getId() %>">
                        <i class="fa-solid fa-trash"></i> Borrar
                    </a>
                    <a class="btn btn-danger" href="UsuarioForm.jsp?id=<%= usuario.getId() %>">
                        <i class="fa-solid fa-pencil"></i> Actualizar
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" align="center">No hay usuarios disponibles.</td>
            </tr>
            <%
                }
            } catch (Exception e) {
            %>
            <tr>
                <td colspan="6" align="center">Error al cargar usuarios: <%= e.getMessage() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

    </div>
</div>
</body>
<%
    }
%>
</html>
