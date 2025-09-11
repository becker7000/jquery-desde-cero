// Obtener el parámetro de la URL (si existe) para saber si estamos editando o creando
$(document).ready(function() {
var urlParams = new URLSearchParams(window.location.search);
var peliculaId = urlParams.get('id');

// Si el ID está presente, vamos a editar la película
if (peliculaId) {
  $.ajax({
    url: '/web-service-restful-jpa/api/peliculas/' + peliculaId,  // Actualizada con la URI correcta
    type: 'GET',
    dataType: 'json',
    success: function(pelicula) {
      $('#peliculaId').val(pelicula.id);
      $('#titulo').val(pelicula.titulo);
      $('#anioLanzamiento').val(pelicula.anioLanzamiento);
      $('#genero').val(pelicula.genero);
      $('#duracion').val(pelicula.duracion);
      $('#submitPelicula').text('Actualizar Película');
    },
    error: function(xhr, status, error) {
      alert('Error al cargar la película para edición');
    }
  });
}
});

// Enviar el formulario para crear o actualizar la película
$('#formPelicula').submit(function(e) {
e.preventDefault();

var pelicula = {
  titulo: $('#titulo').val(),
  anioLanzamiento: $('#anioLanzamiento').val(),
  genero: $('#genero').val(),
  duracion: $('#duracion').val()
};

var id = $('#peliculaId').val();

if (id) {
  // Si hay ID, es una actualización
  $.ajax({
    url: '/web-service-restful-jpa/api/peliculas/' + id,  // Actualizada con la URI correcta
    type: 'PUT',
    contentType: 'application/json',
    data: JSON.stringify(pelicula),
    success: function(response) {
      alert('Película actualizada');
      window.location.href = 'gestor-peliculas.html';  // Redirigir al listado de películas
    },
    error: function(xhr, status, error) {
      alert('Error al actualizar la película');
    }
  });
} else {
  // Si no hay ID, es una creación
  $.ajax({
    url: '/web-service-restful-jpa/api/peliculas',  // Actualizada con la URI correcta
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(pelicula),
    success: function(response) {
      alert('Película creada');
      window.location.href = 'gestor-peliculas.html';  // Redirigir al listado de películas
    },
    error: function(xhr, status, error) {
      alert('Error al crear la película');
    }
  });
}
});
