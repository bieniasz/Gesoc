package domain.entities.seguridad;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import db.DAOs.ContraseniasPreviasDAO;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import db.DAOs.IntentosFallidosDAO;
import domain.entities.usuario.Usuario;

import java.util.*;

public class AlmacenContrasenias extends EntidadPersistente {

    private Integer periodosDeRotacion = 3;

    private ContraseniasPreviasDAO contraseniasPreviasDAO;
    private IntentosFallidosDAO intentosFallidosDAO;

    public void setContraseniasPreviasDAO(ContraseniasPreviasDAO contraseniasPreviasDAO) {
        this.contraseniasPreviasDAO = contraseniasPreviasDAO;
    }

    public void setIntentosFallidosDAO(IntentosFallidosDAO intentosFallidosDAO) {
        this.intentosFallidosDAO = intentosFallidosDAO;
    }

    public void registrarContrasenia(Usuario usuario, String contrasenia) {
        //ver que excepcion tira el dao si no encuentra una entrada.
        try {
            ContraseniasPrevias contraseniasPrevias = this.contraseniasPreviasDAO.getContraseniasPrevias(usuario);
            contraseniasPrevias.agregarContrasenia(contrasenia);
            if (contraseniasPrevias.getContrasenias().size() > this.periodosDeRotacion) {
                contraseniasPrevias.removerContrseniaVieja();
            }

            this.contraseniasPreviasDAO.persistirContraseniaPrevia(contraseniasPrevias);

        }
        catch (Exception exception) {
            ContraseniasPrevias contraseniasPrevias = new ContraseniasPrevias();
            contraseniasPrevias.setUsuarioId(usuario.getId());
            contraseniasPrevias.setContrasenia(new ArrayList<String>());
            contraseniasPrevias.agregarContrasenia(contrasenia);

            this.contraseniasPreviasDAO.persistirContraseniaPrevia(contraseniasPrevias);
        }
    }

    public boolean contraseniaRepiteContraseniasViejas(Usuario usuario, String contrasenia) {
        ContraseniasPrevias contraseniasPrevias = this.contraseniasPreviasDAO.getContraseniasPrevias(usuario);

        try {
            return contraseniasPrevias.getContrasenias().contains(contrasenia);
        } catch (Exception e) {
            return false;
        }
    }

    public void setPeriodosDeRotacion(Integer cantPeriodos){
        this.periodosDeRotacion = cantPeriodos;
    }

    public List<String> getContraseniasPreviasDeUsuario(Usuario usuario) {
        ContraseniasPrevias contraseniasPrevias = this.contraseniasPreviasDAO.getContraseniasPrevias(usuario);

        return contraseniasPrevias.getContrasenias();
    }

    public boolean compararContrasenia(Usuario usuario,String contrasenia) {
        try {
            List<String> contraseniasPrevias = getContraseniasPreviasDeUsuario(usuario);
            Integer posicionUltimaContrasenia = contraseniasPrevias.size()-1;
            if (contraseniasPrevias.get(posicionUltimaContrasenia) == contrasenia) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existeUsuario(Usuario usuario){
        try {
            List<String> contraseniasPreviasDeUsuario = getContraseniasPreviasDeUsuario(usuario);
            if (contraseniasPreviasDeUsuario.size() > 0){

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void agregarIntentoFallido(Usuario usuario) {
        // TODO si no hay intento fallido tengo que crear uno nuevo, primero ver que error tira hibernate
        IntentosFallidos intentosFallidos = this.intentosFallidosDAO.getIntentosFallidos(usuario);

        try {
            intentosFallidos.nuevoIntentoFallido();
            this.intentosFallidosDAO.persistirIntentoFallido(intentosFallidos);

        } catch (NullPointerException n) {

            IntentosFallidos intento = new IntentosFallidos();
            intento.setUsuarioId(usuario.getId());
            intento.nuevoIntentoFallido();

            this.intentosFallidosDAO.persistirIntentoFallido(intento);
        }
    }


    public IntentosFallidos getIntentosFallidosDeUsuario(Usuario usuario){
        return this.intentosFallidosDAO.getIntentosFallidos(usuario);
    }

    public void reiniciarIntentosFallidos(Usuario usuario){
        this.intentosFallidosDAO.getIntentosFallidos(usuario).reiniciarIntentos();
    }

    public void setHoraDelIntentoMaximo(Usuario usuario){
        this.intentosFallidosDAO.getIntentosFallidos(usuario).setHoraDelIntentoMaximo();
    }

    public void crearIntentoFallidoSiAplica(Usuario usuario){
        // TODO fijarse que error retorna el DAO
        // si existe un intento fallido no hacer nada, si no, crearlo1

        Boolean esElPrimerIntento = this.getIntentosFallidosDeUsuario(usuario) == null;
        if (this.existeUsuario(usuario) && esElPrimerIntento){
            IntentosFallidos intento = new IntentosFallidos();
            intento.setUsuarioId(usuario.getId());

            this.intentosFallidosDAO.persistirIntentoFallido(intento);
        }
    }
}
