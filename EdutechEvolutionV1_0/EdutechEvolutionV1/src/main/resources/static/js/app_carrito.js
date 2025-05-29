const carrito = (()=>{
    const API = "/api/v1/carrito";

    async function listarCarrito(){
        try{
            const response = await fetch(API);
            const libros = await response.json();
            const tbody = document.querySelector("#tablaCarrito tbody")
            const TotalLibro = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            TotalLibro.textContent = libros.length;

            libros.forEach(libro =>{
                const fila = `
                <tr>
                    <td>${libro.id}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>
                        <button onClick="carrito.eliminarLibro(${libro.id})">Eliminar</button>
                    <td>
                <tr>

                
                `;
                tbody.innerHTML += fila;
            })

        }catch(err) {
            console.error("Error al cargar el carrito",err);
        }
    }
    async function agregarLibro(id){
        try{
            await fetch(`${API}/agregar/${id}`,
                {method : "POST"}
            );
            alert("Libro agrgado al carrito");
            listarCarrito();

        }catch (err) {
            console.error("Error al agregar al carrito",err);
        }
    }
    async function eliminarLibro(id){
        try{
            await fetch(`${API}eliminar/${id}`,{method:"DELETE"});
            alert ("El libro se elimino del carro");
            listarCarrito();
        }catch (err){
            console.error("Error al elminiar el libro del carrito", err);
        }
    }
    async function vaciarCarrito(){
        if(confirm("¿Estas seguro de vaciar el carrito?")){
            await fetch(`${API}/vaciar`,{method: "DELETE"});
            alert ("El carrito esta vacio.");
            listarCarrito();
        }
    }
   
    async function confirmarCompra(){
       const total = document.getElementById("totalCarrito").textContent;
       if (parseInt(total) === 0) {
        alert("El carrito esta vacío.");
        return;
       }
       if(confirm(`¿Desea confirmar su compra por ${total}?`)){
        await fetch (`${API}/vaciar`,{method:"DELETE"});
        alert ("¡Gracias por su compra!")
        listarCarrito();
       }
    }
    return {listarCarrito, agregarLibro, eliminarLibro, vaciarCarrito, confirmarCompra};

})();