package domain.entities.seguridad;

import db.DAOs.UserDAO;
import domain.entities.config.Config;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import domain.entities.usuario.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeUsuario implements iValidadorDeUsuario{

    //TODO: armar builder para tener todos los criterios seteados
    private final List<CriterioValidacion> criteriosCreacionContrasenia = new ArrayList<>();
    private AlmacenContrasenias almacenContrasenias;
    private UserDAO usuarioDao;

    public ValidadorDeUsuario() {
    }

    public void setAlmacenContrasenias(AlmacenContrasenias almacenContrasenias) { this.almacenContrasenias = almacenContrasenias; }
    public void setUsuarioDao(UserDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    public void agregarCriterioDeCreacionDeContrasenia(CriterioValidacion criterio) {
        this.criteriosCreacionContrasenia.add(criterio);
    }


    public List<String> validarCreacionContrasenia(String nombreUsuario, String contrasenia) {
        final List<String> errores = new ArrayList<String>();
        try {
            final Usuario user = this.usuarioDao.buscarUsuarioPoruserId(nombreUsuario);
            if(user == null)
                throw new UsuarioContraseniaInvalidosException();

            //TODO sacar esta lista de errores y manejarlo por try catch
            this.criteriosCreacionContrasenia.forEach(criterio -> {
                try {
                    criterio.validar(user, contrasenia, errores);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            if (errores.size() == 0) {
                // TODO
                this.almacenContrasenias.registrarContrasenia(user, contrasenia);
            }
        } catch (UsuarioContraseniaInvalidosException e) {
            e.printStackTrace();
            errores.add(e.getMessage());
        }

        return errores;
    }


    public Usuario validarLogin(String nombreUsuario, String contrasenia, LocalDateTime horaIntento) throws UsuarioContraseniaInvalidosException, LoginBloqueadoTemporalmenteException {
        Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId(nombreUsuario);
        if(usuario == null)
            throw new UsuarioContraseniaInvalidosException();

        this.validarContrasenia(usuario, contrasenia);
        this.validarTiempoLogin(usuario, horaIntento);

        return usuario;
    }

    private void validarContrasenia(Usuario usuario, String contrasenia) throws UsuarioContraseniaInvalidosException {
        boolean passCoincide = BCrypt.checkpw(contrasenia, usuario.getContrasenia());
        if(!passCoincide){
            this.almacenContrasenias.registrarIntentoFallido(usuario);
            throw new UsuarioContraseniaInvalidosException();
        }
    }

    private void validarTiempoLogin(Usuario usuario, LocalDateTime horaIntento) throws LoginBloqueadoTemporalmenteException {
        IntentosFallidos intentosFallidos = almacenContrasenias.getIntentosFallidosDeUsuario(usuario);

        if (intentosFallidos != null) {
            if(intentosFallidos.getIntentosRealizados() >= Config.login_topeIntentosFallidos) {
                LocalDateTime horaIntentoMaximo = intentosFallidos.getHoraDelIntentoMaximo();
                if (this.cumpleCondicionDeEspera(horaIntentoMaximo, horaIntento)) {
                    this.almacenContrasenias.reiniciarIntentosFallidos(usuario);
                } else {
                    throw new LoginBloqueadoTemporalmenteException();
                }
            } else {
                this.almacenContrasenias.reiniciarIntentosFallidos(usuario);
            }
        }
    }

    private boolean cumpleCondicionDeEspera(LocalDateTime desde, LocalDateTime hasta) {
        int distanciaEntreTiempos = (int) Duration.between(desde, hasta).getSeconds();
        return distanciaEntreTiempos >= Config.login_tiempoDeEspera;
    }
}
