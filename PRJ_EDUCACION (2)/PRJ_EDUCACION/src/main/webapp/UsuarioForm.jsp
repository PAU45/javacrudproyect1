<%@ page import="com.tecsup.prj_educacion.modelo.entities.Usuarios" %>
<%@ page import="com.tecsup.prj_educacion.services.UsuariosService" %>
<%@ page import="com.tecsup.prj_educacion.modelo.entities.Administrador" %>
<%@ page import="com.tecsup.prj_educacion.services.impl.UsuariosServiceImpl" %>
<%@ page import="com.tecsup.prj_educacion.util.Tipo" %>
<%@ page import="com.tecsup.prj_educacion.util.Util" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agregar Usuario</title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Incluye tus estilos aquí -->
</head>
<body>

<%
    HttpSession misesion = request.getSession();
    if (misesion.getAttribute("eladministrador") == null) {
        response.sendRedirect("error.jsp");
        return; // Asegúrate de salir después de redirigir
    }

    Administrador adm = (Administrador) misesion.getAttribute("eladministrador");
    String nombre = adm.getNombres() + " " + adm.getApellidos();

    Tipo tipo = Util.opc;
    UsuariosService usuariosServicio = new UsuariosServiceImpl(tipo);
    String mensajeError = ""; // Para almacenar mensajes de error

    // Comprobar si se han enviado datos desde el formulario
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String nombreStr = request.getParameter("nombre");
        String correoStr = request.getParameter("correo");
        String contrasenaStr = request.getParameter("contrasena");
        String telefonoStr = request.getParameter("telefono");
        String direccionStr = request.getParameter("direccion");

        // Validar que no haya campos vacíos
        if (nombreStr != null && !nombreStr.isEmpty() &&
                correoStr != null && !correoStr.isEmpty() &&
                contrasenaStr != null && !contrasenaStr.isEmpty() &&
                telefonoStr != null && !telefonoStr.isEmpty() &&
                direccionStr != null && !direccionStr.isEmpty()) {
            try {
                // Asignar un id o gestionarlo según tu lógica
                int id = 0; // Puedes cambiar esto si tienes una forma de generar IDs automáticamente.
                Usuarios nuevoUsuario = new Usuarios(id, nombreStr, correoStr, contrasenaStr, telefonoStr, direccionStr);

                // Agregar el nuevo usuario
                usuariosServicio.grabar(nuevoUsuario);

                // Redirigir a la página de usuarios después de agregar
                response.sendRedirect("usuariosMan.jsp");
                return; // Asegúrate de salir después de redirigir
            } catch (Exception e) {
                mensajeError = "Error al agregar usuario: " + e.getMessage();
            }
        } else {
            mensajeError = "Por favor, completa todos los campos.";
        }
    }
%>

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">AGREGAR USUARIO</h3>
                </div>
                <div class="card-body">
                    <form action="UsuarioForm.jsp" method="POST">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombre:</label>
                            <input class="form-control" type="text" name="nombre" required placeholder="Nombre"/>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Correo:</label>
                            <input class="form-control" type="email" name="correo" required placeholder="Correo"/>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Contraseña:</label>
                            <input class="form-control" type="password" name="contrasena" required placeholder="Contraseña"/>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Teléfono:</label>
                            <input class="form-control" type="text" name="telefono" required placeholder="Teléfono"/>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Dirección:</label>
                            <input class="form-control" type="text" name="direccion" required placeholder="Dirección"/>
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
</div>

</body>
</html>
