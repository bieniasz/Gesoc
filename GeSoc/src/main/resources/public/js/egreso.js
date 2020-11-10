function mostrarBuscarEgreso() {
  document.getElementById("buscarEgreso").classList.toggle("show");
}

function llenarEgreso(egreso) {
    document.getElementById("egreso").value = egreso;  
    document.getElementById("buscarEgreso").classList.toggle("show");
}