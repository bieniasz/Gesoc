function mostrarBuscarProveedor() {
  document.getElementById("buscarProveedor").classList.toggle("show");
}

function filterFunction() {
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


function agregarItem() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function guardarNuevoItem() {
    var table = document.getElementById("detalleEgresos");
    var row = table.insertRow(1);
    row.insertCell(0)
    var descripcion = row.insertCell(1);
    var cantidad = row.insertCell(2);
    var valor = row.insertCell(3);
    row.insertCell(4);
    descripcion.innerHTML = document.getElementById("descripcionItem").value;
    cantidad.innerHTML = document.getElementById("cantidadItem").value;
    valor.innerHTML = document.getElementById("valorItem").value;
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
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}
