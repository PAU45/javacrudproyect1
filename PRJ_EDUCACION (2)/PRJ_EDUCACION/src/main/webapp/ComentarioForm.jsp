<%@ page import="com.tecsup.prj_educacion.modelo.entities.Administrador" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Comentarios" %>
<%@ page import="com.tecsup.prj_educacion.services.ComentariosService" %>
<%@ page import="com.tecsup.prj_educacion.services.impl.ComentariosServiceImpl" %>
<%@ page import="com.tecsup.prj_educacion.util.Tipo" %>
<%@ page import="com.tecsup.prj_educacion.util.Util" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agregar Comentario</title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Incluye tus estilos aquí -->
</head>
<body>

    <%
    HttpSession misesion = request.getSession();
    if (misesion.getAttribute("eladministrador") == null) {
        response.sendRedirect("error.jsp");
    } else {
        Administrador adm = (Administrador) misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();

        Tipo tipo = Util.opc;
        ComentariosService comentariosServicio = new ComentariosServiceImpl(tipo);

        String mensajeError = ""; // Para almacenar mensajes de error

        // Comprobar si se han enviado datos desde el formulario
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String calificacionStr = request.getParameter("calificacion");
            String comentarioStr = request.getParameter("comentario");

            if (calificacionStr != null && comentarioStr != null) {
                try {
                    int calificacion = Integer.parseInt(calificacionStr);
                    int id = 0; // Asignar un id o gestionarlo según tu lógica
                    Comentarios nuevoComentario = new Comentarios(id, calificacion, comentarioStr, LocalDateTime.now());

                    // Agregar el nuevo comentario
                    comentariosServicio.grabar(nuevoComentario);

                    // Redirigir a la página de mantenimiento después de agregar
                    response.sendRedirect("ComentariosMan.jsp");
                } catch (NumberFormatException e) {
                    mensajeError = "Error: La calificación debe ser un número.";
                } catch (Exception e) {
                    mensajeError = "Error al agregar comentario: " + e.getMessage();
                }
            }
        }
%>

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">AGREGAR COMENTARIO</h3>
                </div>
                <div class="card-body">
                    <form action="ComentarioForm.jsp" method="POST">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Calificación:</label>
                            <input class="form-control" type="number" name="calificacion" required min="1" max="5" placeholder="Calificación"/>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Comentario:</label>
                            <textarea class="form-control" name="comentario" required placeholder="Comentario"></textarea>
                        </div>

                        <% if (!mensajeError.isEmpty()) { %>
                        <div class="alert alert-danger mt-2"><%= mensajeError %></div>
                        <% } %>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input class="btn btn-success" type="submit" value="Agregar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<% } %>
</body>
</html>