package db;

import db.DAOs.ValidadorTransparenciaDAOMySQL;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestValidadorTransparenciaDAO {
    ValidadorTransparenciaDAOMySQL dao = new ValidadorTransparenciaDAOMySQL();

    @Test
    public void TestPersistirResultadoValidacion() {
        ResultadoDeValidacion resultado = new ResultadoDeValidacion();
        dao.guardarResultadoDeValidacion(resultado);
    }

    @Test
    public void TestRecuperarResultadoValidacion(){
        ResultadoDeValidacion resultadoDeValidacion = dao.buscarResultado(1);
        Assert.assertNotNull(resultadoDeValidacion);
        Assert.assertEquals(false, resultadoDeValidacion.getLeido());
    }

    @Test
    public void TestActualizarResultadoValidacion() {
        ResultadoDeValidacion resultadoDeValidacion = dao.buscarResultado(1);
        resultadoDeValidacion.setFecha(LocalDate.now());
        resultadoDeValidacion.marcarLeido();
        dao.modificarResultadoDeValidacion(resultadoDeValidacion);
    }
}
