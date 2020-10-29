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

    private Integer tiempoDeEspera;
    private Integer cantidadMaximaDeIntentos;
    private AlmacenContrasenias almacen;

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
                LocalDateTime horaDelIntentoFallidoMaximo = intentosFallidos.getHoraDelIntentoMaximo();
                int tiempoDesdeIntentoFallidoMaximo = distanciaEnSegundosEntreTiempos(horaDelIntentoFallidoMaximo, horaActual);
                if (!this.cumpleCondicionDeEspera(tiempoDesdeIntentoFallidoMaximo)) {
                    mensajesDeError.add("Debe esperar " + (tiempoDeEspera - tiempoDesdeIntentoFallidoMaximo) + " segundos para volver loguearse.");
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
        int distanciaEntreTiempos;
        if (desde != null){
            Duration duracion = Duration.between(desde,hasta);
            distanciaEntreTiempos = (int) duracion.getSeconds();
        } else {
            distanciaEntreTiempos = this.tiempoDeEspera + 1;
        }

        return distanciaEntreTiempos;
    }

    private boolean cumpleCondicionDeEspera(int tiempoEntreLogins){
        return tiempoEntreLogins >= this.tiempoDeEspera;
    }
}