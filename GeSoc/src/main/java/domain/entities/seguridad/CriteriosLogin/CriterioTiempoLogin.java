package domain.entities.seguridad.CriteriosLogin;

import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.CriterioValidacion;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class CriterioTiempoLogin implements CriterioValidacion {

    public CriterioTiempoLogin() {
    }

    private final Integer tiempoDeEspera;
    private final Integer cantidadMaximaDeIntentos;
    private final AlmacenContrasenias almacen;

    public CriterioTiempoLogin(AlmacenContrasenias almacen) {

        this.tiempoDeEspera = 5; // va a ser considerado en segundos
        this.cantidadMaximaDeIntentos = 3;
        this.almacen = almacen;
    }

    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
        LocalDateTime horaActual = LocalDateTime.now();  //.compareTo();
        IntentosFallidos intentosFallidos = almacen.getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null) {
            if (intentosFallidos.getCantidadIntentos() >= cantidadMaximaDeIntentos) {
                LocalDateTime horaDelIntentoMaximo = intentosFallidos.getHoraDelIntentoMaximo();
                int tiempoEntreLoginsMaximo = distanciaEnSegundosEntreTiempos(horaDelIntentoMaximo, horaActual);
                if (this.cumpleCondicionDeEspera(usuario, tiempoEntreLoginsMaximo) == false) {
                    mensajesDeError.add("Debe esperar " + (tiempoDeEspera - tiempoEntreLoginsMaximo) + " segundos para volver loguearse.");
                    } else {
                        this.almacen.reiniciarIntentosFallidos(usuario);
                }
            }
        }
    }


    public void errorAlLogear(Usuario usuario) {
        IntentosFallidos intentosFallidos = this.almacen.getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null){
            almacen.agregarIntentoFallido(usuario);

            if (intentosFallidos.getCantidadIntentos() == this.cantidadMaximaDeIntentos) {
                almacen.setHoraDelIntentoMaximo(usuario);
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

    //TODO: para que un domain.entities.usuario en este metodo?
    private boolean cumpleCondicionDeEspera(Usuario usuario, int tiempoEntreLogins){
        if (tiempoEntreLogins >= this.tiempoDeEspera){

            return true;
        }else return false;
    }


}