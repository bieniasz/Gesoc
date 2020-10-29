package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class IntentosFallidosDAOMySQL implements IntentosFallidosDAO {
    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) throws NoResultException {
        IntentosFallidos intentosFallidos;
        Query query = EntityManagerHelper.createQuery("from IntentosFallidos i where i.usuarioId = :user");
        query.setParameter("user", usuario.getId());
        intentosFallidos = (IntentosFallidos) query.getSingleResult();
        return intentosFallidos;
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {

    }

    public IntentosFallidos buscarIntentosFallidosPorId(Integer id){
        return EntityManagerHelper.getEntityManager().find(IntentosFallidos.class, id);
    }
}
