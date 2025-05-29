const nombre = sessionStorage.getItem("nombreUsuario");
    if (nombre){
        document.getElementById("Mensaje").textContent = `Bienvenidos, ${nombre}`;        
    }
    function cerrarSesion(){
        sessionStorage.clear();
        window.location.href = "/login.html"
    }