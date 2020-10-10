package seguridad.ContraseniasPrevias;

import db.EntityManagerHelper;
import usuario.Usuario;

import javax.persistence.Query;
import java.util.List;


public class ContraseniasPreviasMySQL implements ContraseniasPreviasDAO{


    @Override
    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        //EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = EntityManagerHelper.createQuery("SELECT cp FROM ContraseniasPrevias cp WHERE cp.usuarioId = :user");
        query.setParameter("user", usuario.getid());
        List<ContraseniasPrevias> contrasenias = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return new ContraseniasPrevias();
    }

    @Override
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(contraseniasPrevias);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }
}
