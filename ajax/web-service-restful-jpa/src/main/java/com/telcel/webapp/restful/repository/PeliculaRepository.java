package com.telcel.webapp.restful.repository;

import com.telcel.webapp.restful.model.Pelicula;

import java.util.List;

public interface PeliculaRepository {

    List<Pelicula> listar();
    Pelicula guardar(Pelicula pelicula);
    Pelicula buscarPorId(int id);
    void eliminar(int id);

}
