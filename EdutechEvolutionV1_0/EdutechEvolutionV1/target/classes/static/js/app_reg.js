function registrar(){
    fetch("http://localhost:8080/api/v2/usuarios/registrar", {
        method: "POST",
        Headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            nombre: document.getElementById("nombre").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        })
    }).then(res => res.json())
    .then(data => alert("Usuario registrado con ID: "+data.id));
}