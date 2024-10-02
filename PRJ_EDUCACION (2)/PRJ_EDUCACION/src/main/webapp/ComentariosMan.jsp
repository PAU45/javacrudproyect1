<%@ page import="java.util.List" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Administrador" %>
<%@ page import="com.tecsup.prj_educacion.services.ComentariosService" %>
<%@ page import="com.tecsup.prj_educacion.services.impl.ComentariosServiceImpl" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Comentarios" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.tecsup.prj_educacion.util.Tipo" %>
<%@ page import="com.tecsup.prj_educacion.util.Util" %>
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
        ComentariosService comentariosServicio = new ComentariosServiceImpl(tipo); // Inicializar el servicio de comentarios

        // Formateador para la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Comentarios! <%= nombre %></title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Incluye tus estilos aquí -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <!-- Para los íconos -->
</head>
<body>

<div class="container" style="margin-top:70px;">
    <div class="row mt-5">
        <jsp:include page="master.jsp" />

        <h1>Mantenimiento de Comentarios!</h1>
        <div style="padding: 10px;">
            <button class="btn btn-danger">
                <a class="nav-link link-light" aria-current="page" href="ComentarioForm.jsp"> Nuevo Comentario</a>
            </button>
        </div>

        <table class="table table-dark table-hover">
            <thead>
            <tr align="center">
                <th>ID</th>
                <th>CALIFICACIÓN</th>
                <th>COMENTARIO</th>
                <th>FECHA</th>
                <th>ACCIONES</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    List<Comentarios> comentarios = comentariosServicio.listar();
                    if (comentarios != null && !comentarios.isEmpty()) {
                        for (Comentarios comentario : comentarios) {
            %>
            <tr>
                <td><%= comentario.getId() %></td>
                <td><%= comentario.getCalificacion() %></td>
                <td><%= comentario.getComentario() %></td>
                <td><%= comentario.getFecha().format(formatter) %></td>
                <td>
                    <a class="btn btn-warning" href="comentariosController?accion=eliminar&id=<%= comentario.getId() %>">
                        <i class="fa-solid fa-trash"></i>Borrar
                    </a>
                    <a class="btn btn-danger" href="EditarComentario.jsp?id=<%= comentario.getId() %>">
                        <i class="fa-solid fa-pencil"></i>Actualizar
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" align="center">No hay comentarios disponibles.</td>
            </tr>
            <%
                }
            } catch (Exception e) {
            %>
            <tr>
                <td colspan="5" align="center">Error al cargar comentarios: <%= e.getMessage() %></td>
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