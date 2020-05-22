package seguridad;
import usuario.Usuario;

import java.time.LocalTime;
import java.util.List;

public class CriterioTiempoLogin implements CriterioValidacion {

    private final Integer tiempoDeEspera;
    private LocalTime intentoAnterior;

    public CriterioTiempoLogin() {

        this.tiempoDeEspera = 3;
        this.intentoAnterior = LocalTime.now();
    }

    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {
        LocalTime horaActual = LocalTime.now();  //.compareTo();

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