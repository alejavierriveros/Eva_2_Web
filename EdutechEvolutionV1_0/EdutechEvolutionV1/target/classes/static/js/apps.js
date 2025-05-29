const API_URL = "http://localhost:8080/api/v1/cursos"; 


function listarCursos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(cursos => {
            const tbody = document.querySelector("#tablaCursos tbody");
            tbody.innerHTML = "";
            cursos.forEach(curso => {
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.nombre}</td>
                        <td>${curso.descripcion}</td>
                        <td>${curso.fechaInicio}</td>
                        <td>${curso.cupos}</td>
                        <td>${curso.seccion}</td>
                        <td>${curso.precio}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${curso.id})">🗑️ Eliminar</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarCurso(${curso.id})">✏️ Editar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}


function agregarCurso() {
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const seccion = document.getElementById("seccion").value;
    const fechaInicio = document.getElementById("fechaInicio").value;
    const cupos = parseInt(document.getElementById("cupos").value);
    const precio = parseFloat(document.getElementById("precio").value);

    if (!nombre || !descripcion || !seccion || !fechaInicio || !cupos || !precio) {
        alert("Completa todos los campos correctamente.");
        return;
    }

    const nuevoCurso = {
        nombre,
        descripcion,
        seccion,
        fechaInicio,
        cupos,
        precio
    };

    fetch("http://localhost:8080/api/v1/cursos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoCurso)
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al agregar curso");
        return response.json();
    })
    .then(data => {
        alert("Curso agregado correctamente");
        listarCursos(); 
    })
    .catch(error => {
        alert("Error al enviar curso: " + error.message);
        console.error(error);
    });
}




function eliminarCurso(id) {
    fetch(`${API_URL}/eliminar/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Curso eliminado exitosamente");
                listarCursos();
            }
        });
}


let cursoEnEdicionId = null;

function buscarCurso(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(curso => {
            document.getElementById("nombre").value = curso.nombre;
            document.getElementById("descripcion").value = curso.descripcion;
            document.getElementById("fechaInicio").value = curso.fechaInicio;
            document.getElementById("cupos").value = curso.cupos;
            document.getElementById("seccion").value = curso.seccion;
            document.getElementById("precio").value = curso.precio;

            cursoEnEdicionId = curso.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Curso";
                boton.onclick = function() {
                    actualizarCurso(curso.id);
                };
            }
        });
}


function actualizarCurso(id) {
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const fechaInicio = document.getElementById("fechaInicio").value;
    const cupos = parseInt(document.getElementById("cupos").value);
    const seccion = document.getElementById("seccion").value;
    const precio = parseInt(document.getElementById("precio").value);

    const cursoActualizado = {
        id,
        nombre,
        descripcion,
        fechaInicio,
        cupos,
        seccion,
        precio
    };

    fetch(`${API_URL}/actualizar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cursoActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Curso actualizado exitosamente");
        listarCursos();
        limpiarFormulario();
    });
}


function limpiarFormulario() {
    document.getElementById("nombre").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("fechaInicio").value = "";
    document.getElementById("cupos").value = "";
    document.getElementById("seccion").value = "";
    document.getElementById("precio").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Curso";
    boton.setAttribute("onclick", "agregarCurso()");

    cursoEnEdicionId = null;
}


listarCursos();
