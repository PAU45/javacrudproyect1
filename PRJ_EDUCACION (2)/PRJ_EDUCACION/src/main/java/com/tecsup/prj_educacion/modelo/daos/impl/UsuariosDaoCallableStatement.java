package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.UsuariosDao;
import com.tecsup.prj_educacion.modelo.entities.Usuarios;
import com.tecsup.prj_educacion.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDaoCallableStatement implements UsuariosDao {

    private Connection con;
    private ResultSet rs;

    @Override
    public List<Usuarios> findAll() {
        List<Usuarios> usuarios = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_findAll_usuarios()}")) {
            rs = cst.executeQuery();
            while (rs.next()) {
                Usuarios usuario = new Usuarios(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public void create(Usuarios usuario) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_ins_usuario(?,?,?,?,?)}")) {
            cst.setString(1, usuario.getNombre());
            cst.setString(2, usuario.getCorreo());
            cst.setString(3, usuario.getContrasena());
            cst.setString(4, usuario.getTelefono());
            cst.setString(5, usuario.getDireccion());
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Usuarios find(Integer id) {
        Usuarios usuario = null;
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_usuario(?)}")) {
            cst.setInt(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                usuario = new Usuarios(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public void update(Usuarios usuario) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_upd_usuario(?,?,?,?,?,?)}")) {
            cst.setInt(1, usuario.getId());
            cst.setString(2, usuario.getNombre());
            cst.setString(3, usuario.getCorreo());
            cst.setString(4, usuario.getContrasena());
            cst.setString(5, usuario.getTelefono());
            cst.setString(6, usuario.getDireccion());
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_del_usuario(?)}")) {
            cst.setInt(1, id);
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }
}
