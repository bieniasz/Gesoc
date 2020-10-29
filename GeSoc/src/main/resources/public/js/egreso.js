function mostrarBuscarEgreso() {
  document.getElementById("buscarEgreso").classList.toggle("show");
}

function llenarEgreso(egreso, id) {
    document.getElementById("egreso").value = egreso;
    document.getElementById("egresoId").value = id;
    document.getElementById("buscarEgreso").classList.toggle("show");
}