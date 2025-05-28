
const API_CURSOS = "http://localhost:8080/api/v2/cursos";
const API_CARRITO = "http://localhost:8080/api/v2/carrito";

document.addEventListener("DOMContentLoaded", () => {
    cargarCursos();
    cargarCarrito();
});

async function cargarCursos() {
    const res = await fetch(API_CURSOS);
    const cursos = await res.json();
    const tbody = document.getElementById("listaCursos");
    tbody.innerHTML = "";
    cursos.forEach(curso => {
        tbody.innerHTML += `
            <tr>
                <td>${curso.id}</td>
                <td>${curso.nombre}</td>
                <td>${curso.descripcion}</td>
                <td>
                    <button class="btn btn-success btn-sm" onclick="agregarAlCarrito(${curso.id})">Agregar al carrito</button>
                </td>
            </tr>
        `;
    });
}

async function cargarCarrito() {
    const res = await fetch(API_CARRITO);
    const cursos = await res.json();
    const tbody = document.getElementById("tablaCarrito");
    tbody.innerHTML = "";
    cursos.forEach(curso => {
        tbody.innerHTML += `
            <tr>
                <td>${curso.id}</td>
                <td>${curso.nombre}</td>
                <td>${curso.descripcion}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${curso.id})">Eliminar</button>
                </td>
            </tr>
        `;
    });

    const totalRes = await fetch(API_CARRITO + "/total");
    const total = await totalRes.text();
    document.getElementById("totalCursos").textContent = total;
}

async function agregarAlCarrito(id) {
    await fetch(`${API_CARRITO}/agregar/${id}`, { method: "POST" });
    cargarCarrito();
}

async function eliminarDelCarrito(id) {
    await fetch(`${API_CARRITO}/eliminar/${id}`, { method: "DELETE" });
    cargarCarrito();
}

async function vaciarCarrito() {
    await fetch(`${API_CARRITO}/vaciar`, { method: "DELETE" });
    cargarCarrito();
}
