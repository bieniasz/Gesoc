package domain.entities.seguridad.CriteriosLogin;

import domain.entities.config.Config;
import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.CriterioValidacion;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.usuario.Usuario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class CriterioTiempoLogin implements CriterioValidacion {

    private final AlmacenContrasenias almacen;

    public CriterioTiempoLogin(AlmacenContrasenias almacen) {
        this.almacen = almacen;
    }

    /**
     * Si los datos de login son correctos, se valida que el mismo no esté bloqueado temporalmente
     * por superar la cantidad de intentos fallidos máxima. En caso afirmativo, se muestra
     * cuánto tiempo debe esperar para volver a intentarlo.
     **/
    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) throws LoginBloqueadoTemporalmenteException {
        LocalDateTime horaActual = LocalDateTime.now();
        IntentosFallidos intentosFallidos = almacen.getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null
                && intentosFallidos.getIntentosRealizados() == Config.login_topeIntentosFallidos) {

            LocalDateTime horaIntentoMaximo = intentosFallidos.getHoraDelIntentoMaximo();
            if (this.cumpleCondicionDeEspera(horaIntentoMaximo, horaActual)) {
                this.almacen.reiniciarIntentosFallidos(usuario);
            } else {
                throw new LoginBloqueadoTemporalmenteException();
            }
        }
    }


    //TODO obsoleto. reemplazar en los test
    public void errorAlLogear(Usuario usuario) {
        IntentosFallidos intentosFallidos = this.almacen.getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null) {
            almacen.registrarIntentoFallido(usuario);
            if (intentosFallidos.getIntentosRealizados() == Config.login_topeIntentosFallidos) {
                almacen.setHoraDelIntentoMaximo(usuario);
            }
        }
    }

    private boolean cumpleCondicionDeEspera(LocalDateTime desde, LocalDateTime hasta) {
        int distanciaEntreTiempos = (int) Duration.between(desde, hasta).getSeconds();
        return distanciaEntreTiempos >= Config.login_tiempoDeEspera;
    }
}