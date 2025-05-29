document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/api/v2/usuarios/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    });

    const data = await response.json();

    const errorMessage = document.getElementById("errorMessage");
    const welcomeMessage = document.getElementById("welcomeMessage");

    if (data.result === "OK") {
        // Al login exitoso, redirige a la página de cursos
        localStorage.setItem("userName", data.nombre);  // Guarda el nombre en el almacenamiento local
        window.location.href = "cursos.html";  // Redirige a la página de cursos
    } else {
        // Muestra el error si las credenciales son incorrectas
        errorMessage.textContent = "Correo o contraseña incorrectos.";
        errorMessage.style.display = "block";
        welcomeMessage.style.display = "none";
    }
});
