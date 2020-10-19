package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.Direccion;

public class ProveedorDAOMySQL implements ProveedorDAO{

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getCiudad());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getProvincia());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getPais());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getMonedaLocal());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal());
        EntityManagerHelper.getEntityManager().persist(proveedor);
        EntityManagerHelper.commit();
    }

    @Override
    public Proveedor getProveedor(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Proveedor.class, id);
    }
}
