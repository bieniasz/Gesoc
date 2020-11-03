package db.DAOs;

import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

public interface IntentosFallidosDAO {

    IntentosFallidos getIntentosFallidos(Usuario usuario);
    void persistirIntentoFallido(IntentosFallidos intentoFallido);
    void modificarIntentoFallido(IntentosFallidos intentosFallidos);
    void eliminarIntentoFallido(Usuario usuario);
}
