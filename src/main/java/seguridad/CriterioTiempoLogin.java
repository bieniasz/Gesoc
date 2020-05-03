package seguridad;
//TODO imports sin uso hay que borrarlos.
import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO no se que significa aca <intentoAnterior>, para usar generics deberiamos tener una interface o una abstract
 * class con un <T> o <E>. ver la documentacion y uso de generics.
 * @param <intentoAnterior>
 */

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