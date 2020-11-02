function mostrarBuscarMedioDePago() {
    document.getElementById("buscarMedioDePago").classList.toggle("show");
}

function llenarMedioDePago(idMercadoPago, descMercadoPago, tipoMercadoPago, id) {
    document.getElementById("descripcionDelPago").value = descMercadoPago;
    document.getElementById("tipoDePago").value = tipoMercadoPago;
    document.getElementById("medioDePagoIdDB").value = id;

    document.getElementById("buscarMedioDePago").classList.toggle("show");
}