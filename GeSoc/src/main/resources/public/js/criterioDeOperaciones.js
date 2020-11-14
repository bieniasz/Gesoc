function mostrarBuscarCriterio() {
  document.getElementById("buscarCriterio").classList.toggle("show");
}

function llenarCriterio(criterio) {

    document.getElementById("criterio").value = criterio;
    document.getElementById("buscarCriterio").classList.toggle("show");

    var $rows = $("#tabla3 tbody");
    $rows.show();

    $("#tabla3 tbody").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(criterio.toLowerCase()) > -1)
        });

}

function limpiarFiltro(){

    document.getElementById("criterio").value = "";

    var $rows = $("#tabla3 tbody");
        $rows.show();
}

