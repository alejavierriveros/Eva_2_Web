window.onload = async function() {
    const userName = localStorage.getItem("userName");
    if (!userName) {
        // Si no hay un usuario, redirige a la página de login
        window.location.href = "index.html";
    }

    document.querySelector(".header h2").textContent = `Bienvenido, ${userName}`;

    // Obtén los cursos desde el backend
    const response = await fetch("http://localhost:8080/api/v2/cursos");
    const cursos = await response.json();

    const coursesContainer = document.getElementById("coursesContainer");

    cursos.forEach(curso => {
        const cursoCard = document.createElement("div");
        cursoCard.classList.add("course-card");
        
        cursoCard.innerHTML = `
            <h3>${curso.nombre}</h3>
            <p>${curso.descripcion}</p>
            <p class="price">Precio: $${curso.precio}</p>
            <p>Cupos disponibles: ${curso.cupos}</p>
            <p>Fecha de inicio: ${curso.fechaInicio}</p>
        `;
        
        coursesContainer.appendChild(cursoCard);
    });

    // Acción de logout
    document.getElementById("logoutButton").addEventListener("click", function() {
        localStorage.removeItem("userName");  // Elimina el usuario del almacenamiento local
        window.location.href = "index.html";  // Redirige al login
    });
};
