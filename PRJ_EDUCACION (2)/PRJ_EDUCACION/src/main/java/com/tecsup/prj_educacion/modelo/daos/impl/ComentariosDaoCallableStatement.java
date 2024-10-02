package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.ComentariosDao;
import com.tecsup.prj_educacion.modelo.entities.Comentarios;
import com.tecsup.prj_educacion.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentariosDaoCallableStatement implements ComentariosDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public List<Comentarios> findAll() {
        List<Comentarios> comentarios = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_findAll_comentarios()}")) {
            rs = cst.executeQuery();
            while (rs.next()) {
                Comentarios comentario = new Comentarios(
                        rs.getInt("id"),
                        rs.getInt("calificacion"),
                        rs.getString("comentario"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                );
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return comentarios;
    }

    @Override
    public void create(Comentarios comentario) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_ins_comentario(?,?,?)}")) {
            cst.setInt(1, comentario.getCalificacion());
            cst.setString(2, comentario.getComentario());
            cst.setTimestamp(3, Timestamp.valueOf(comentario.getFecha()));
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Comentarios find(Integer id) {
        Comentarios comentario = null;
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_comentario(?)}")) {
            cst.setInt(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                comentario = new Comentarios(
                        rs.getInt("id"),
                        rs.getInt("calificacion"),
                        rs.getString("comentario"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return comentario;
    }

    @Override
    public void update(Comentarios comentario) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_upd_comentario(?,?,?,?)}")) {
            cst.setInt(1, comentario.getId());
            cst.setInt(2, comentario.getCalificacion());
            cst.setString(3, comentario.getComentario());
            cst.setTimestamp(4, Timestamp.valueOf(comentario.getFecha()));
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_del_comentario(?)}")) {
            cst.setInt(1, id);
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }
}