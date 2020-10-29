package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

public class ValidadorTransparenciaDAOMySQL implements ValidadorTransparenciaDAO {

    @Override
    public void guardarResultadoDeValidacion(ResultadoDeValidacion resultado) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(resultado);
        EntityManagerHelper.commit();
    }

    @Override
    public ResultadoDeValidacion buscarResultado(Integer id) {
        return EntityManagerHelper.getEntityManager().find(ResultadoDeValidacion.class, id);
    }

    @Override
    public void modificarResultadoDeValidacion(ResultadoDeValidacion resultadoDeValidacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(resultadoDeValidacion);
        EntityManagerHelper.commit();
    }
}
