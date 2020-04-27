package seguridad;

import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class CriterioTiempoLogin<intentoAnterior> implements CriterioValidacion {


    private Integer tiempoDeEspera;
    private LocalTime intentoAnterior;

    public CriterioTiempoLogin() {
        this.tiempoDeEspera = 3;
    }


    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        LocalTime horaActual = LocalTime.now();  //.compareTo();
        horaActual.toSecondOfDay();

        try {

            Integer tiempoEntreLogins = horaActual.toSecondOfDay() - this.intentoAnterior.toSecondOfDay();
            if ( tiempoEntreLogins <= this.tiempoDeEspera) {
                mensajesDeError.add("Debe esperar mas de tres segundos para volver a intentar");
            }
        } catch (Exception e){

        }

        this.intentoAnterior = horaActual;
    }
}