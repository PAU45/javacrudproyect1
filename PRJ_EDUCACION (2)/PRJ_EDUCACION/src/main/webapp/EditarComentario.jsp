<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Administrador" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Comentarios" %>
<%@ page import="com.tecsup.prj_educacion.services.ComentariosService" %>
<%@ page import="com.tecsup.prj_educacion.services.impl.ComentariosServiceImpl" %>
<%@ page import="com.tecsup.prj_educacion.util.Tipo" %>
<%@ page import="com.tecsup.prj_educacion.util.Util" %>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if (misesion.getAttribute("eladministrador") == null) {
        response.sendRedirect("error.jsp");
    } else {
        Administrador adm = (Administrador) misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
        int id = Integer.parseInt(request.getParameter("id"));

        // Crear el tipo necesario
        Tipo tipo = Util.opc; // Asegúrate de que esto sea correcto según tu aplicación
        ComentariosService servicio = new ComentariosServiceImpl(tipo);

        Comentarios comentario = servicio.buscar(id);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar Comentario - <%= nombre %></title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Incluye tus estilos aquí -->
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">EDITAR COMENTARIO</h3>
                </div>
                <div class="card-body">
                    <form action="comentariosController" method="POST"> <!-- Cambiado a comentariosController -->
                        <input type="hidden" name="id" value="<%= comentario.getId() %>">

                        <div class="input-group mt-2">
                            <label class="input-group-text">Calificación:</label>
                            <input class="form-control" type="number" name="txtCalificacion"
                                   value="<%= comentario.getCalificacion() %>" required min="1" max="5">
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Comentario:</label>
                            <textarea class="form-control" name="txtComentario" required
                                      placeholder="Escribe tu comentario aquí..."><%= comentario.getComentario() %></textarea>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input name="accion" type="hidden" value="actualizar"/>
                            <input class="btn btn-success" type="submit" value="Actualizar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<% } %> <!-- Cierre de la condición del administrador -->
</body>
</html>>