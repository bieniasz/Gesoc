package db;

import db.DAOs.ProveedorDAOMySQL;
import db.DAOs.UserDAOMySQL;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.Direccion;
import domain.entities.direccion.DireccionBuilder;
import domain.entities.direccion.MLDireccionesService;
import domain.entities.usuario.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class TestUserDao {
    private UserDAOMySQL userDAO = new UserDAOMySQL();


    @Test
    public void TestBuscarUserPorId() {
        Usuario usuPorUserID = userDAO.buscarUsuarioPoruserId("aos");

        Assert.assertEquals(2, usuPorUserID.getId());
    }

}
