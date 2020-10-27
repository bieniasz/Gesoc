package domain.entities.bandejaDeResultado;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

public class TestLeerResultadoDeValidacion {

    private BandejaDeResultado bandeja;
    private ResultadoDeValidacion resultado0;
    private ResultadoDeValidacion resultado1;
    private ResultadoDeValidacion resultado2;
    private ResultadoDeValidacion resultadoALeer;

    @Before
    public void init(){

        this.bandeja = new BandejaDeResultado();
        this.resultado0 = new ResultadoDeValidacion();
        this.resultado1 = new ResultadoDeValidacion();
        this.resultado2 = new ResultadoDeValidacion();
        this.bandeja.setResultadosDeValidacion(resultado0);
        this.bandeja.setResultadosDeValidacion(resultado1);
        this.bandeja.setResultadosDeValidacion(resultado2);
        this.resultadoALeer = this.bandeja.getResultadosDeValidacion().get(1);
    }

    @Test
    public void leerResultado() {
       bandeja.leerResultado(this.resultadoALeer);
       Assert.assertTrue(resultado1.getLeido());
    }
}
