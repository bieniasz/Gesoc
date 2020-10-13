package db.DAOs;

import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

public interface IntentosFallidosDAO {

    public IntentosFallidos getIntentosFallidos(Usuario usuario);
    public void persistirIntentoFallido(IntentosFallidos intentoFallido);
}
