window.onload = async function() {
    const userName = localStorage.getItem("userName");
    if (!userName) {
        window.location.href = "index.html";
    }

    document.querySelector(".header h2").textContent = `Bienvenido, ${userName}`;

    // Obtén los cursos desde el backend
    const response = await fetch("http://localhost:8080/api/v2/cursos");
    const cursos = await response.json();

    const coursesContainer = document.getElementById("coursesContainer");
    const cartCount = document.getElementById("cartCount");

    // Actualizar el contador del carrito
    async function updateCartCount() {
        const cartResponse = await fetch("http://localhost:8080/api/v2/carrito/total");
        const total = await cartResponse.json();
        cartCount.textContent = total;
    }

    // Cargar cursos
    cursos.forEach(curso => {
        const cursoCard = document.createElement("div");
        cursoCard.classList.add("course-card");
        
        cursoCard.innerHTML = `
            <h3>${curso.nombre}</h3>
            <p>${curso.descripcion}</p>
            <p class="price">Precio: $${curso.precio}</p>
            <p>Cupos disponibles: ${curso.cupos}</p>
            <p>Fecha de inicio: ${curso.fechaInicio}</p>
            <button class="add-to-cart" data-id="${curso.id}">Agregar al carrito</button>
        `;
        
        coursesContainer.appendChild(cursoCard);
    });

    // Agregar curso al carrito
    coursesContainer.addEventListener("click", async function(event) {
        if (event.target.classList.contains("add-to-cart")) {
            const cursoId = event.target.getAttribute("data-id");
            const response = await fetch(`http://localhost:8080/api/v2/carrito/agregar/${cursoId}`, {
                method: "POST"
            });
            const message = await response.text();
            alert(message);
            updateCartCount();
        }
    });

    // Navegar al carrito
    document.getElementById("cartButton").addEventListener("click", function() {
        window.location.href = "cart.html";
    });

    // Acción de logout
    document.getElementById("logoutButton").addEventListener("click", function() {
        localStorage.removeItem("userName");
        window.location.href = "index.html";
    });

    // Inicializar el contador del carrito
    updateCartCount();
};