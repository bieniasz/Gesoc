package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.Direccion;
import domain.entities.operacionComercial.CategoriaDeOperaciones;

import javax.persistence.NoResultException;
import java.util.List;

public class ProveedorDAOMySQL implements ProveedorDAO{

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        EntityManagerHelper.beginTransaction();
        /*EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getCiudad());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getProvincia());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getPais());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal().getMonedaLocal());
        EntityManagerHelper.getEntityManager().persist(proveedor.getDireccionPostal());*/
        EntityManagerHelper.getEntityManager().persist(proveedor);
        EntityManagerHelper.commit();
    }

    @Override
    public Proveedor getProveedor(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Proveedor.class, id);
    }

    @Override
    public List<Proveedor> getTodosLosProveedores() {

        List<Proveedor> proveedorList;
        try{
            proveedorList = (List<Proveedor>) EntityManagerHelper
                    .createQuery("from Proveedor")
                    .getResultList();
        } catch (NoResultException e){
            proveedorList = null;
        }
        return proveedorList;
    }

    public Proveedor buscarProveedorPorNombre(String nombreProveedor) {
        Proveedor proveedor = (Proveedor) EntityManagerHelper.createQuery(
                "from Proveedor where nombreApellido_RazonSocial = '"+nombreProveedor+"'").getSingleResult();

        return proveedor;
    }

    public void actualizarProveedor(Proveedor proveedor) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(proveedor);
        EntityManagerHelper.commit();
    }
}
