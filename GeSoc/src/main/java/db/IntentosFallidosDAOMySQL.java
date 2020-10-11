package db;

import seguridad.IntentosFallidos.IntentosFallidos;
import seguridad.IntentosFallidos.IntentosFallidosDAO;
import usuario.Usuario;

public class IntentosFallidosDAOMySQL implements IntentosFallidosDAO {
    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) {
        return null;
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {

    }
}
