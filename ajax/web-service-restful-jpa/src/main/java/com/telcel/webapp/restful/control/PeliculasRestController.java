package com.telcel.webapp.restful.control;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.telcel.webapp.restful.model.Pelicula;
import com.telcel.webapp.restful.service.PeliculaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/peliculas")
@Produces(MediaType.APPLICATION_JSON)
@JsonPropertyOrder({"id","titulo","anioLanzamiento","genero","duracion"})
public class PeliculasRestController {

    @Inject
    private PeliculaService peliculaService;

    @GET
    public List<Pelicula> listar(){
        return peliculaService.listar();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id){
        // Se puede delegar al service:
        Optional<Pelicula> peliculaOptional = peliculaService.buscarPorId(id);
        if(peliculaOptional.isPresent()){
            return Response.ok(peliculaOptional.get()).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Pelicula pelicula){
        try{
            Pelicula peliculaNueva = peliculaService.guardar(pelicula);
            return Response.ok(peliculaNueva).build();
        }catch (Exception exception){
            exception.printStackTrace();
            return Response.serverError().build();
        }
    }
    /*
    *  Para leer los headers:
    *  public Response crear(Pelicula pelicula, @Context HttpHeaders headers){
    *  String headerAut = headers.getHeaderString("Authorization");
    * */

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") int id, Pelicula pelicula){
        // NOTA: podríamos delegar este bloque al service
        Optional<Pelicula> peliculaOptional = peliculaService.buscarPorId(id);
        if(peliculaOptional.isPresent()){
            Pelicula peliculaEditar = peliculaOptional.get();
            peliculaEditar.setTitulo(pelicula.getTitulo());
            peliculaEditar.setAnioLanzamiento(pelicula.getAnioLanzamiento());
            peliculaEditar.setGenero(pelicula.getGenero());
            peliculaEditar.setDuracion(pelicula.getDuracion());
            try {
                peliculaService.guardar(peliculaEditar);
                return Response.ok(peliculaEditar).build();
            }catch (Exception exception){
                exception.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id){
        Optional<Pelicula> peliculaOptional = peliculaService.buscarPorId(id);
        if(peliculaOptional.isPresent()){
            try{
                peliculaService.eliminar(peliculaOptional.get().getId());
                return Response.noContent().build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}

// TODO: implementar una lista de series (con todas sus operaciones correspondientes)
