<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Usuarios" %>
<%
    // Aquí deberías obtener el usuario por ID desde el servicio
    int id = Integer.parseInt(request.getParameter("id"));
    Usuarios usuario = // Lógica para obtener el usuario por ID
%>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>
<h1>Editar Usuario</h1>
<form action="UsuarioEditar.jsp" method="post">
    <input type="hidden" name="_method" value="update" />
    <input type="hidden" name="id" value="${usuario.id}" />
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required /><br/>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${usuario.email}" required /><br/>
    <button type="submit">Actualizar</button>
</form>
<a href="usuariosMan.jsp">Volver a la lista</a>
</body>
</html>