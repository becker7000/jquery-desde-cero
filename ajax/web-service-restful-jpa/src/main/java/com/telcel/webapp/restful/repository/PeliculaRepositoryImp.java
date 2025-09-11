package com.telcel.webapp.restful.repository;

import com.telcel.webapp.restful.model.Pelicula;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class PeliculaRepositoryImp implements PeliculaRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Pelicula> listar() {
        return entityManager.createQuery("SELECT p FROM Pelicula p").getResultList();
    }

    @Override
    public Pelicula guardar(Pelicula pelicula) {
        if(pelicula.getId()>0){
            entityManager.merge(pelicula); // En caso de edición
        }else{
            entityManager.persist(pelicula); // En caso de crear una película nueva
        }
        return pelicula;
    }

    @Override
    public Pelicula buscarPorId(int id) {
        return entityManager.find(Pelicula.class,id);
    }

    @Override
    public void eliminar(int id) {
        Pelicula pelicula = buscarPorId(id); // Obtenemos la película a eliminar
        entityManager.remove(pelicula); // si existe la eliminamos
    }
}
