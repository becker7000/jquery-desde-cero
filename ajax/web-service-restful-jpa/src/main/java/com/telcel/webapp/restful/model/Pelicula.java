package com.telcel.webapp.restful.model;

import jakarta.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titulo", length = 45)
    private String titulo;

    @Column(name = "anio_lanzamiento")
    private int anioLanzamiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "duracion")
    private int duracion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {

        /*return String.format(
                """
                
                    +-----------PELICULA------------+
                    | Id: %d
                    | Titulo: %s
                    | Anio de lanzamiento: %d
                    | Genero: %s
                    | Duracion: %d
                    +-------------------------------+
                """,id,titulo,anioLanzamiento,genero,duracion
        );
        */
        return String.format("\n\t +-----------PELICULA------------+\n" +
                "                | Id: %d\n" +
                "                | Titulo: %s\n" +
                "                | Anio de lanzamiento: %d\n" +
                "                | Genero: %s\n" +
                "                | Duracion: %d\n" +
                "                +-------------------------------+");
    }

}
