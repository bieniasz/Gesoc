package validadorTransparencia.criterioMenorValor;

import operacionComercial.OperacionEgreso;
import validacionSeleccionProveedor.criteriosSeleccionProveedor.MenorValor;

public class AuxMenorValor extends MenorValor {
    //Esta clase existe para darle un valor de retorno ficticio a validar
    //y poder testear los casos de exito unitariamente
    //sin involucrar la suscripcion de validacion

    public String validarMock(OperacionEgreso operacionEgreso) throws Exception {
        super.validar(operacionEgreso);
        return "La Operacion de Egreso cumple con todas las politicas";
    }
}
