function agregarItem() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

var cantidadDeItemsDeEgreso = 0;
function guardarNuevoItem() {
    var table = document.getElementById("detalleEgresos");
    var row = table.insertRow(1);

    row.insertCell(0)
    var descripcion = row.insertCell(1);
    var descripcionInput = document.createElement("INPUT");
    descripcionInput.setAttribute("id", "descripcionItem" + cantidadDeItemsDeEgreso);
    descripcionInput.setAttribute("name", "descripcionItem" + cantidadDeItemsDeEgreso);
    descripcionInput.setAttribute("type", "text");
    descripcionInput.setAttribute("value", document.getElementById("descripcionItem").value);
    descripcion.appendChild(descripcionInput);

    var cantidad = row.insertCell(2);
    var cantidadInput = document.createElement("INPUT");
    cantidadInput.setAttribute("id", "cantidadItem" + cantidadDeItemsDeEgreso);
    cantidadInput.setAttribute("name", "cantidadItem" + cantidadDeItemsDeEgreso);
    cantidadInput.setAttribute("type", "number");
    cantidadInput.setAttribute("value", document.getElementById("cantidadItem").value);
    cantidad.appendChild(cantidadInput);

    var valor = row.insertCell(3);
    var valorInput = document.createElement("INPUT");
    valorInput.setAttribute("id", "valorItem" + cantidadDeItemsDeEgreso);
    valorInput.setAttribute("name", "valorItem" + cantidadDeItemsDeEgreso);
    valorInput.setAttribute("type", "number");
    valorInput.setAttribute("value", document.getElementById("valorItem").value);
    valor.appendChild(valorInput);
    row.insertCell(4);

    cantidadDeItemsDeEgreso = cantidadDeItemsDeEgreso + 1;
    document.getElementById("cantidadDetalles").value = cantidadDeItemsDeEgreso;
    //document.getElementById("modalAgregarDetalle").style.display = 'none';

    document.getElementById("descripcionItem").value = null;
    document.getElementById("cantidadItem").value = null;
    document.getElementById("valorItem").value = null;
}

function borrarItem(id) {
    document.getElementById('rowItemExistente' + id).remove();
}