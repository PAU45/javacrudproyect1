package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.CursoDao;
import com.tecsup.prj_educacion.modelo.entities.Curso;
import com.tecsup.prj_educacion.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoCallableStatement implements CursoDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_findAll_curso()}")){
            rs = cst.executeQuery();
            while(rs.next()){
                cursos.add(new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos")));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");

        }
        return cursos;
    }

    @Override
    public void create(Curso curso) {
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_ins_curso(?,?,?)}")){
            cst.setString(1, curso.getCodigo());
            cst.setString(2, curso.getNombre());
            cst.setInt(3, curso.getCreditos());
            cst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la inserción");
        }

    }

    @Override
    public Curso find(String id) {
        Curso curso = null;
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_find_curso(?)}")){
            cst.setString(1, id);
            rs = cst.executeQuery();
            if(rs.next()){
                curso = new Curso(rs.getString("chrCurCodigo"), rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos"));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return curso;
    }

    @Override
    public void update(Curso curso) {
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_upd_curso(?,?,?)}")){
            cst.setString(1, curso.getCodigo());
            cst.setString(2, curso.getNombre());
            cst.setInt(3, curso.getCreditos());
            cst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la actualización");
        }
    }

    @Override
    public void delete(String id) {
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_del_curso(?)}")){
            cst.setString(1, id);
            cst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la eliminación");
        }
    }

}
