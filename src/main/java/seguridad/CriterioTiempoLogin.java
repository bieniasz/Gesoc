package seguridad;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class CriterioTiempoLogin<intentoAnterior> implements CriterioValidacion {


    private Integer tiempoDeEspera;
    Date intentoAnterior;

    public CriterioTiempoLogin() {
        this.tiempoDeEspera = 3;

    }


    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        Date horaActual = new Date();

        if ((horaActual.getTime() - intentoAnterior.getTime()) > tiempoDeEspera) {
            mensajesDeError.add("Debe esperar mas de tres segundos para volver a intentar");
        }

        Date intentoAnterior = horaActual;
    }


}