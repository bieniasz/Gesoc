package domain.entities.bandejaDeResultado.filtroDeResultado;

import domain.entities.bandejaDeResultado.BandejaDeResultado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestFiltrarPorLeidoYFechaResultadosDeValidacion {
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
        this.resultado0.setFecha(LocalDate.parse("2020-01-01"));
        this.resultado1.setFecha(LocalDate.parse("2020-01-05"));
        this.resultado2.setFecha(LocalDate.parse("2020-01-10"));
        this.resultadosEsperados = new ArrayList<>();
        this.resultadosFiltrados = new ArrayList<>();
        this.bandeja.recibirResultado(resultado0);
        this.bandeja.recibirResultado(resultado1);
        this.bandeja.recibirResultado(resultado2);


    }

    @Test
    public void filtrarDesde01_01_2020Hasta10_01_2020YLeidos_DosResultadosCoincidentes() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(2));
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde05_01_2020Hasta10_01_2020YLeidos_DosResultadosCoincidentes(){
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(2));
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-05"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde05_01_2020Hasta05_01_2020YLeidos_UnicoResultadosCoincidente(){
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(2));
        this.resultadosEsperados.add(resultado1);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-05"), LocalDate.parse("2020-01-05")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde05_01_2020Hasta05_01_2020YLeidos_SinResultadosCoincidentes() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(0));
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-05"), LocalDate.parse("2020-01-05")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde05_01_2020Hasta05_01_2020YLeidos_UnicoResultadoExistenteYCoincidente() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.resultadosEsperados.add(resultado1);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-05"), LocalDate.parse("2020-01-05")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde01_01_1999Hasta31_12_2050YLeidos_SinResultadosCoincidentes() {
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("1999-01-01"), LocalDate.parse("2050-12-31")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde01_01_1999Hasta31_12_2050YLeidos_DosResultadosCoincidentes() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(2));
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("1999-01-01"), LocalDate.parse("2050-12-31")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde01_01_1999Hasta31_12_2050YLeidos_UnicoResultadoCoincidente() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(0));
        this.resultadosEsperados.add(resultado0);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("1999-01-01"), LocalDate.parse("2050-12-31")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde01_01_1999Hasta31_12_2050YLeidos_TodosLosResultadosCoincidentes() {
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(0));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(1));
        this.bandeja.leerResultado(this.bandeja.getResultadosDeValidacion().get(2));
        this.resultadosEsperados.add(resultado0);
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroLeido());
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("1999-01-01"), LocalDate.parse("2050-12-31")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }
}
