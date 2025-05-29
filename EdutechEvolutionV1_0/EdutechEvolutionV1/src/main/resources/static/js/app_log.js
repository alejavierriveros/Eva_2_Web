
const API_URL = "http://localhost:8080/api/v1/usuarios/login";

function login() {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    
    if (!email || !password) {
        alert("Por favor, completa todos los campos.");
        return;
    }

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"  
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error en la respuesta del servidor");
        }
        return response.json();
    })
    .then(data => {
        if (data.result === "OK") {
           
            sessionStorage.setItem("nombreUsuario", data.nombre);
            
            window.location.href = "index.html";
        } else {
            alert("Correo o contraseña incorrectos.");
        }
    })
    .catch(error => {
        console.error("Error durante el login:", error);
        alert("Hubo un problema al conectar con el servidor.");
    });
}
