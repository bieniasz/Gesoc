package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.direccion.Direccion;

public class DireccionDAOMySQL implements  DireccionDao{
    //TODO: hibernate cuando no encuentra algo no falla, retorna null
    //TODO: mejorar todo los dao de direccion para evitar duplicar informacion que viene de ML
    public void guardarDireccion(Direccion direccion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(direccion.getCiudad());
        EntityManagerHelper.getEntityManager().persist(direccion.getProvincia());
        EntityManagerHelper.getEntityManager().persist(direccion.getPais());
        EntityManagerHelper.getEntityManager().persist(direccion.getMonedaLocal());
        EntityManagerHelper.getEntityManager().persist(direccion);
        EntityManagerHelper.commit();
    }

    public void guardarCiudad(Ciudad ciudad){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(ciudad);
        EntityManagerHelper.commit();
    }

    public void guardarProvincia(Provincia provincia){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(provincia);
        EntityManagerHelper.commit();
    }

    public void guardarPais(Pais pais){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(pais);
        EntityManagerHelper.commit();
    }

    public void guardarMoneda(Moneda moneda){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(moneda);
        EntityManagerHelper.commit();
    }
}
