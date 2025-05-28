
const apiUrl = "http://localhost:8080/api/v2/cursos";

document.addEventListener("DOMContentLoaded", listarCursos);
document.getElementById("cursoForm").addEventListener("submit", guardarCurso);

async function listarCursos() {
    const res = await fetch(apiUrl);
    const cursos = await res.json();
    const tbody = document.querySelector("#tablaCursos tbody");
    tbody.innerHTML = "";
    cursos.forEach(curso => {
        tbody.innerHTML += `
            <tr>
                <td>${curso.id}</td>
                <td>${curso.nombre}</td>
                <td>${curso.descripcion}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editarCurso(${curso.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="eliminarCurso(${curso.id})">Eliminar</button>
                </td>
            </tr>
        `;
    });
}

async function guardarCurso(e) {
    e.preventDefault();
    const id = document.getElementById("cursoId").value;
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;

    const curso = { nombre, descripcion };
    const method = id ? "PUT" : "POST";
    const url = id ? `${apiUrl}/${id}` : apiUrl;

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(curso)
    });

    resetForm();
    listarCursos();
}

async function editarCurso(id) {
    const res = await fetch(`${apiUrl}/${id}`);
    const curso = await res.json();
    document.getElementById("cursoId").value = curso.id;
    document.getElementById("nombre").value = curso.nombre;
    document.getElementById("descripcion").value = curso.descripcion;
}

async function eliminarCurso(id) {
    if (confirm("¿Estás seguro de eliminar este curso?")) {
        await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
        listarCursos();
    }
}

function resetForm() {
    document.getElementById("cursoForm").reset();
    document.getElementById("cursoId").value = "";
}
