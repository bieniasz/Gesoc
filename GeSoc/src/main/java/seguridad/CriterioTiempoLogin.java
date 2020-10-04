package seguridad;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class CriterioTiempoLogin implements CriterioValidacion {

    private final Integer tiempoDeEspera;
    private final Integer cantidadMaximaDeIntentos;

    public CriterioTiempoLogin() {

        this.tiempoDeEspera = 5; // va a ser considerado en segundos

        this.cantidadMaximaDeIntentos = 3;
    }
/*
    @Override
    public void validar(String usuario, String contrasenia, List<String> mensajesDeError) {
        LocalDateTime horaActual = LocalDateTime.now();  //.compareTo();
        IntentosFallidos intentosFallidos = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos.getCantidadIntentos() >= cantidadMaximaDeIntentos) {
            LocalDateTime horaDelIntentoMaximo = intentosFallidos.getHoraDelIntentoMaximo();
            int tiempoEntreLoginsMaximo = distanciaEnSegundosEntreTiempos(horaDelIntentoMaximo, horaActual);
            if (this.cumpleCondicionDeEspera(usuario, tiempoEntreLoginsMaximo) == false) {
                mensajesDeError.add("Debe esperar " + (tiempoDeEspera - tiempoEntreLoginsMaximo) + " segundos para volver loguearse.");
                } else {
                    AlmacenContrasenias.Instancia().reiniciarIntentosFallidos(usuario);
            }
        }


    }


    public void errorAlLogear(String usuario) {
        IntentosFallidos intentosFallidos = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null){
            AlmacenContrasenias.Instancia().agregarIntentoFallido(usuario);

            if (intentosFallidos.getCantidadIntentos() == this.cantidadMaximaDeIntentos) {
                AlmacenContrasenias.Instancia().setHoraDelIntentoMaximo(usuario);
            }
        }
    }

    private int distanciaEnSegundosEntreTiempos(LocalDateTime desde, LocalDateTime hasta){
        if (desde != null){
            Duration duracion = Duration.between(desde,hasta);
            int distanciaEntreTiempos = (int) duracion.getSeconds();
            return distanciaEntreTiempos;
            }else {
                return (this.tiempoDeEspera + 1);
        }

    }

    private boolean cumpleCondicionDeEspera(String usuario, int tiempoEntreLogins){
        if (tiempoEntreLogins >= this.tiempoDeEspera){

            return true;
        }else return false;
    }

 */
}