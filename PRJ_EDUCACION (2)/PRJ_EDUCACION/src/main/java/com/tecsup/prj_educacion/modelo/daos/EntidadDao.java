package com.tecsup.prj_educacion.modelo.daos;

import java.util.List;

public interface EntidadDao <T,V>{
    public void create(T t);
    public void update(T t);
    public void delete(V id);
    public T find(V id);
    public List<T> findAll();
}

