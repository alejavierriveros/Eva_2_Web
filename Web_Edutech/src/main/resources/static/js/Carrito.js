window.onload = async function() {
    const userName = localStorage.getItem("userName");
    if (!userName) {
        window.location.href = "index.html";
    }

    const cartContainer = document.getElementById("cartContainer");
    const cartTotal = document.getElementById("cartTotal");

    // Actualizar el carrito
    async function updateCart() {
        const response = await fetch("http://localhost:8080/api/v2/carrito/path");
        const cursos = await response.json();

        cartContainer.innerHTML = "";
        cursos.forEach(curso => {
            const cartItem = document.createElement("div");
            cartItem.classList.add("cart-item");
            cartItem.innerHTML = `
                <div>
                    <h3>${curso.nombre}</h3>
                    <p>${curso.descripcion}</p>
                    <p class="price">Precio: $${curso.precio}</p>
                </div>
                <button class="remove-from-cart" data-id="${curso.id}">Eliminar</button>
            `;
            cartContainer.appendChild(cartItem);
        });

        // Actualizar el total
        const totalResponse = await fetch("http://localhost:8080/api/v2/carrito/total");
        const total = await totalResponse.json();
        cartTotal.textContent = total;
    }

    // Eliminar curso del carrito
    cartContainer.addEventListener("click", async function(event) {
        if (event.target.classList.contains("remove-from-cart")) {
            const cursoId = event.target.getAttribute("data-id");
            const response = await fetch(`http://localhost:8080/api/v2/carrito/eliminar/${cursoId}`, {
                method: "DELETE"
            });
            const message = await response.text();
            alert(message);
            updateCart();
        }
    });

    // Vaciar carrito
    document.getElementById("clearCartButton").addEventListener("click", async function() {
        const response = await fetch("http://localhost:8080/api/v2/carrito/vaciar", {
            method: "DELETE"
        });
        const message = await response.text();
        alert(message);
        updateCart();
    });

    // Volver a la página de cursos
    document.getElementById("backButton").addEventListener("click", function() {
        window.location.href = "Curso.html";
    });

    // Acción de logout
    document.getElementById("logoutButton").addEventListener("click", function() {
        localStorage.removeItem("userName");
        window.location.href = "index.html";
    });

    // Inicializar el carrito
    updateCart();
};