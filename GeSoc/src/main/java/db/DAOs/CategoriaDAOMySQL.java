package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.CategoriaDeOperaciones;

import java.util.List;

public class CategoriaDAOMySQL implements CategoriaDAO {
    @Override
    public List<CategoriaDeOperaciones> getTodasLasCategorias() {
        return (List<CategoriaDeOperaciones>) EntityManagerHelper
                .createQuery("from CategoriaDeOperaciones")
                .getResultList();
    }

    @Override
    public CategoriaDeOperaciones buscarCategoriaPorId(int idDeLaCategoria) {
        return EntityManagerHelper.getEntityManager().find(CategoriaDeOperaciones.class, idDeLaCategoria);
    }
}
