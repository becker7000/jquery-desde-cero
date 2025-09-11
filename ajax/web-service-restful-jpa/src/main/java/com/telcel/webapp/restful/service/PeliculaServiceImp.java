package com.telcel.webapp.restful.service;

import com.telcel.webapp.restful.model.Pelicula;
import com.telcel.webapp.restful.repository.PeliculaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class PeliculaServiceImp implements PeliculaService{

    @Inject
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> listar() {
        return peliculaRepository.listar();
    }

    @Override
    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.guardar(pelicula);
    }

    @Override
    public Optional<Pelicula> buscarPorId(int id) {
        return Optional.ofNullable(peliculaRepository.buscarPorId(id));
    }

    @Override
    public void eliminar(int id) {
        peliculaRepository.eliminar(id);
    }

}
