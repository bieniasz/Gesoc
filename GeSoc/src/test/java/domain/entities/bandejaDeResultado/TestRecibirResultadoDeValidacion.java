package domain.entities.bandejaDeResultado;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.util.ArrayList;
import java.util.List;

public class TestRecibirResultadoDeValidacion {

    private BandejaDeResultado bandeja;
    private ResultadoDeValidacion resultado1;
    private ResultadoDeValidacion resultado2;
    private ResultadoDeValidacion resultado3;
    private List<ResultadoDeValidacion> resultadosDeLaBandeja;

    @Before
    public void init(){

        this.bandeja = new BandejaDeResultado();
        this.resultado1 = new ResultadoDeValidacion();
        this.resultado3 = new ResultadoDeValidacion();
        this.resultado2 = new ResultadoDeValidacion();
        this.resultadosDeLaBandeja = new ArrayList<>();
        this.resultadosDeLaBandeja.add(resultado1);
        this.resultadosDeLaBandeja.add(resultado2);
        this.resultadosDeLaBandeja.add(resultado3);
    }

    @Test
    public void recibirResultados() {
        this.bandeja.recibirResultado(resultado1);
        this.bandeja.recibirResultado(resultado2);
        this.bandeja.recibirResultado(resultado3);

        Assert.assertEquals(bandeja.getResultadosDeValidacion(), resultadosDeLaBandeja);
    }
}
