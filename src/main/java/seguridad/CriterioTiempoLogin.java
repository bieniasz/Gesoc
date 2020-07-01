package seguridad;

import java.time.LocalTime;
import java.util.List;

public class CriterioTiempoLogin implements CriterioValidacion {

    //private List<IntentosFallidosPorUsuario> intentosFallidosPorUsuarios;
    private final Integer tiempoDeEspera;
    //private final Integer cantidadMaximaDeIntentos;
    private LocalTime intentoAnterior;

    public CriterioTiempoLogin() {

        this.tiempoDeEspera = 3;
        // va a ser considerado en segundos
        //this.cantidadMaximaDeIntentos = 3;
    }

    @Override
    public void validar(String usuario, String contrasenia, List<String> mensajesDeError) {
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