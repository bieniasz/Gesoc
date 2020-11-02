package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class MedioDePagoDAOMySQL implements MedioDePagoDAO{

    @Override
    public void guardarMedioDePago(MedioDePago medioDePago) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(medioDePago);
        EntityManagerHelper.commit();
    }

    @Override
    public MedioDePago buscarMedioDePagoPorId(Integer id) {
        return EntityManagerHelper.getEntityManager().find(MedioDePago.class, id);
    }

    @Override
    public List<MedioDePago> buscarTodosLosMediosDePago() {
        List<MedioDePago> medioDePagos;
        Query query = EntityManagerHelper.createQuery("from MedioDePago");

        try{
            medioDePagos = (List<MedioDePago>) query.getResultList();
        } catch (NoResultException e){
            medioDePagos = null;
            e.printStackTrace();
        }
        return medioDePagos;
    }
}
