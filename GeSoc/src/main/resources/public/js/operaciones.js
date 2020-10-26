function mostrarBuscarProveedor() {
  document.getElementById("buscarProveedor").classList.toggle("show");
}

function mostrarBuscarCategorias() {
  document.getElementById("buscarCategoria").classList.toggle("show");
}

function filterFunction(input, div) {
  var input, filter, ul, li, a, i;
  input = document.getElementById("inputBuscarProveedor");
  filter = input.value.toUpperCase();
  div = document.getElementById("buscarProveedor");
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

function llenarProveedor(proveedor, id) {
    document.getElementById("proveedor").value = proveedor;
    document.getElementById("proveedorId").value = id;
    document.getElementById("buscarProveedor").classList.toggle("show");
}

var cantidadDeCategorias = 0;
function agregarCategoria(descripcion, id) {
    var div = document.getElementById("seccionCategorias");

    var divCategoriaNueva = document.createElement("DIV");
        div.appendChild(divCategoriaNueva);

    var categoria = document.createElement("INPUT");
        categoria.setAttribute("id", "categoria" + cantidadDeCategorias);
        categoria.setAttribute("name", "categoria" + cantidadDeCategorias);
        categoria.setAttribute("type", "text");
        categoria.setAttribute("value", descripcion);
        categoria.setAttribute("class", "w3-tag w3-padding w3-small w3-center w3-yellow");

    var iconoBorrar = document.createElement("I");
        iconoBorrar.setAttribute("class", "fas fa-close");

    var aBorrar = document.createElement("A");
        aBorrar.setAttribute("class", "btn btn-search");
        aBorrar.setAttribute("id", "eliminarCategoria"  + cantidadDeCategorias);
        aBorrar.setAttribute("onclick", "eliminarCategoria(" + cantidadDeCategorias  + ")");
        aBorrar.appendChild(iconoBorrar);

        divCategoriaNueva.appendChild(categoria);
        divCategoriaNueva.appendChild(aBorrar);

    cantidadDeCategorias = cantidadDeCategorias + 1;
    document.getElementById("buscarCategoria").classList.toggle("show");
}

function eliminarCategoria(idCategoria) {
    var categoria = document.getElementById('categoria' + idCategoria);
    categoria.remove();

    var cruzCategoria = document.getElementById('eliminarCategoria' + idCategoria);
        cruzCategoria.remove();
}


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

    cantidadDeItemsDeEgreso = cantidadDeItemsDeEgreso + 1
    document.getElementById("cantidadDetalles").value = cantidadDeItemsDeEgreso
    //document.getElementById("modalAgregarDetalle").style.display = 'none';

    document.getElementById("descripcionItem").value = null;
    document.getElementById("cantidadItem").value = null;
    document.getElementById("valorItem").value = null;
}

       // var descripcion = document.getElementById("descripcionItem").value;
    //var cantidad = document.getElementById("cantidadItem").value;
    //var valor = document.getElementById("valorItem").value;
    //$.ajax({
      //  type: "post",
       // url: "item?descripcion=" + descripcion + "&cantidad=" + cantidad + "&valor=" +valor,
        //success: function(resultado){
          //  location.reload(true);
        //}
    //});


function serializarArchivo() {
    var archivoSerializado = getElementById("documentoComercialAdjunto").serialize();
    return archivoSerializado;

  }
 