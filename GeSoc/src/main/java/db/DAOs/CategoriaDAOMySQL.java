package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.CategoriaDeOperaciones;

import javax.persistence.NoResultException;
import java.util.List;

public class CategoriaDAOMySQL implements CategoriaDAO {
    @Override
    public List<CategoriaDeOperaciones> getTodasLasCategorias() {
        List<CategoriaDeOperaciones> categoriaDeOperacionesList;
        try{
            categoriaDeOperacionesList = (List<CategoriaDeOperaciones>) EntityManagerHelper
                    .createQuery("from CategoriaDeOperaciones")
                    .getResultList();
        } catch (NoResultException e){
            categoriaDeOperacionesList = null;
        }
        return categoriaDeOperacionesList;
    }

    @Override
    public CategoriaDeOperaciones buscarCategoriaPorId(int idDeLaCategoria) {
        return EntityManagerHelper.getEntityManager().find(CategoriaDeOperaciones.class, idDeLaCategoria);
    }
}
