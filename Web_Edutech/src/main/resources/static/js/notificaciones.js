
document.getElementById("formNotificacion").addEventListener("submit", async function (e) {
    e.preventDefault();

    const dto = {
        titulo: document.getElementById("titulo").value,
        mensaje: document.getElementById("mensaje").value
    };

    const res = await fetch("http://localhost:8080/api/notificaciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
    });

    const mensaje = document.getElementById("resultado");
    if (res.ok) {
        mensaje.textContent = "✅ Notificación enviada correctamente.";
        mensaje.style.display = "block";
    } else {
        mensaje.textContent = "❌ Error al enviar notificación.";
        mensaje.classList.replace("text-success", "text-danger");
        mensaje.style.display = "block";
    }
});
