package db;

import db.DAOs.CategoriaDAOMySQL;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCategoriaDAO {
    CategoriaDAOMySQL dao = new CategoriaDAOMySQL();

    @Test
    public void TestRecuperarTodasLasCategorias(){
        List<CategoriaDeOperaciones> todasLasCategorias = dao.getTodasLasCategorias();
        Assert.assertNotNull(todasLasCategorias);
        //Assert.assertEquals(2, todasLasCategorias.size());
    }

    @Test
    public void TestRecuperarCategoriaPorID() {
        CategoriaDeOperaciones categoria = dao.buscarCategoriaPorId(1);
        Assert.assertNotNull(categoria);
        Assert.assertEquals("Categoria 1", categoria.getDescripcion());
    }
}
