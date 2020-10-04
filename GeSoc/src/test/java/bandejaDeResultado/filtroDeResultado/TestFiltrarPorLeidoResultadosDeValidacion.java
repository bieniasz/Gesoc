package bandejaDeResultado.filtroDeResultado;

import bandejaDeResultado.BandejaDeResultado;
import bandejaDeResultado.filtroDeResultado.FiltroFecha;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validadorTransparencia.ResultadoDeValidacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestFiltrarPorLeidoResultadosDeValidacion {
    private BandejaDeResultado bandeja;
    private ResultadoDeValidacion resultado0;
    private ResultadoDeValidacion resultado1;
    private ResultadoDeValidacion resultado2;
    private List<ResultadoDeValidacion> resultadosFiltrados;
    private List<ResultadoDeValidacion> resultadosEsperados;

    @Before
    public void init() {

        this.bandeja = new BandejaDeResultado();
        this.resultado0 = new ResultadoDeValidacion();
        this.resultado1 = new ResultadoDeValidacion();
        this.resultado2 = new ResultadoDeValidacion();
        this.resultadosEsperados = new ArrayList<>();
        this.resultadosFiltrados = new ArrayList<>();
        this.bandeja.recibirResultado(resultado0);
        this.bandeja.recibirResultado(resultado1);
        this.bandeja.recibirResultado(resultado2);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
    }

    @Test
    public void filtrarPorLeidos() {
        this.resultadosEsperados.add(resultado1);
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }
}
