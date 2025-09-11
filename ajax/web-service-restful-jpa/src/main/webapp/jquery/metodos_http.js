// Función para cargar todas las películas en la tabla
function cargarPeliculas() {
$.ajax({
  url: '/web-service-restful-jpa/api/peliculas',  // Actualizada con la URI correcta
  type: 'GET',
  dataType: 'json',
  success: function(peliculas) {
    $('#peliculasTabla tbody').empty();
    peliculas.forEach(function(pelicula) {
      $('#peliculasTabla tbody').append(`
        <tr>
          <td>${pelicula.titulo}</td>
          <td>${pelicula.anioLanzamiento}</td>
          <td>${pelicula.genero}</td>
          <td>${pelicula.duracion} min</td>
          <td>
            <button class="editarBtn" data-id="${pelicula.id}">Editar</button>
            <button class="eliminarBtn" data-id="${pelicula.id}">Eliminar</button>
          </td>
        </tr>
      `);
    });
  },
  error: function(xhr, status, error) {
    alert('Error al cargar las películas');
  }
});
}

// Función para buscar una película por ID
$('#buscarPorId').click(function() {
var id = $('#idBuscar').val();
if (id) {
  $.ajax({
    url: '/web-service-restful-jpa/api/peliculas/' + id,  // Actualizada con la URI correcta
    type: 'GET',
    dataType: 'json',
    success: function(pelicula) {
      alert('Película encontrada: ' + pelicula.titulo);
      $('#idBuscar').val('');
    },
    error: function(xhr, status, error) {
      alert('Película no disponible...');
      $('#idBuscar').val('');
    }
  });
}
});

// Crear nueva película (redirige a otra vista)
$('#crearPeliculaBtn').click(function() {
window.location.href = 'formulario.html'; // Redirigir a la vista de formulario
});

// Eliminar película
$(document).on('click', '.eliminarBtn', function() {
var id = $(this).data('id');
if (confirm('¿Estás seguro de que quieres eliminar esta película?')) {
  $.ajax({
    url: '/web-service-restful-jpa/api/peliculas/' + id,  // Actualizada con la URI correcta
    type: 'DELETE',
    success: function() {
      alert('Película eliminada');
      cargarPeliculas();
    },
    error: function(xhr, status, error) {
      alert('Error al eliminar la película');
    }
  });
}
});

// Editar película (redirige a otra vista)
$(document).on('click', '.editarBtn', function() {
var id = $(this).data('id');
window.location.href = 'formulario.html?id=' + id; // Redirigir a la vista de formulario con el ID
});

// Cargar las películas al cargar la página
$(document).ready(function() {
cargarPeliculas();
});
