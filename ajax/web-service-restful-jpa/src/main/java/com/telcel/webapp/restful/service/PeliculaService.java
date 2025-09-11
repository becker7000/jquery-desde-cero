package com.telcel.webapp.restful.service;

import com.telcel.webapp.restful.model.Pelicula;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface PeliculaService {

    List<Pelicula> listar();
    Pelicula guardar(Pelicula pelicula);
    Optional<Pelicula> buscarPorId(int id);
    void eliminar(int id);

}
