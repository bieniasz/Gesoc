package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.CriterioDeOperaciones;

import javax.persistence.NoResultException;
import java.util.List;

public class CriterioDeOperacionesDAOMySQL implements CriterioDeOperacionesDAO{

    @Override
    public List<CriterioDeOperaciones> getTodasLosCriterios() {
        List<CriterioDeOperaciones> criteriosDeOperacionesList;
        try{
            criteriosDeOperacionesList = (List<CriterioDeOperaciones>) EntityManagerHelper
                    .createQuery("from CriterioDeOperaciones")
                    .getResultList();
        } catch (NoResultException e){
            criteriosDeOperacionesList = null;
        }
        return criteriosDeOperacionesList;
    }
}
