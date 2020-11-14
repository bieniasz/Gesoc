function presupuestoCheck(checked) {

    console.log("CHCKED: " + checked)
    if (checked == 'true') {
        console.log("Llenando checbox");
        document.getElementById("prespuestoElegidoCheck").checked = true;
    }
}