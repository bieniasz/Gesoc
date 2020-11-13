package db.DAOs;


import domain.entities.operacionComercial.CriterioDeOperaciones;

import java.util.List;

public interface CriterioDeOperacionesDAO {
    public List<CriterioDeOperaciones> getTodasLosCriterios();
}
