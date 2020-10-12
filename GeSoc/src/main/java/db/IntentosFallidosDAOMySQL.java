package db;

import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidosDAO;
import domain.entities.usuario.Usuario;

public class IntentosFallidosDAOMySQL implements IntentosFallidosDAO {
    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) {
        return null;
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {

    }
}
