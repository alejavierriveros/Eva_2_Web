
document.getElementById("formReporte").addEventListener("submit", async function (e) {
    e.preventDefault();

    const desde = document.getElementById("desde").value;
    const hasta = document.getElementById("hasta").value;

    const res = await fetch(`http://localhost:8080/api/v2/reportes?desde=${desde}&hasta=${hasta}`);
    const data = await res.json();

    const tbody = document.getElementById("tablaReporte");
    tbody.innerHTML = "";
    data.forEach(row => {
        tbody.innerHTML += `
            <tr>
                <td>${row.curso}</td>
                <td>${row.usuario}</td>
                <td>${row.fecha}</td>
                <td>$${row.monto}</td>
            </tr>
        `;
    });
});
