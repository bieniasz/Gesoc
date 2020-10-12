package domain.entities.bandejaDeResultado.filtroDeResultado;

import domain.entities.bandejaDeResultado.BandejaDeResultado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestFiltrarPorFechaResultadosDeValidacion {
    private BandejaDeResultado bandeja;
    private ResultadoDeValidacion resultado1;
    private ResultadoDeValidacion resultado2;
    private ResultadoDeValidacion resultado3;
    private List<ResultadoDeValidacion> resultadosFiltrados;
    private List<ResultadoDeValidacion> resultadosEsperados;

    @Before
    public void init() {

        this.bandeja = new BandejaDeResultado();
        this.resultado1 = new ResultadoDeValidacion();
        this.resultado3 = new ResultadoDeValidacion();
        this.resultado2 = new ResultadoDeValidacion();
        this.resultado1.setFecha(LocalDate.parse("2020-01-01"));
        this.resultado2.setFecha(LocalDate.parse("2020-01-05"));
        this.resultado3.setFecha(LocalDate.parse("2020-01-10"));
        this.resultadosEsperados = new ArrayList<>();
        this.resultadosFiltrados = new ArrayList<>();
        this.bandeja.recibirResultado(resultado1);
        this.bandeja.recibirResultado(resultado2);
        this.bandeja.recibirResultado(resultado3);
    }

    @Test
    public void filtrarDesde01_01_2020Hasta10_01_2020() {
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.resultadosEsperados.add(resultado3);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde02_01_2020Hasta10_01_2020() {
        this.resultadosEsperados.add(resultado2);
        this.resultadosEsperados.add(resultado3);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-02"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde06_01_2020Hasta10_01_2020() {
        this.resultadosEsperados.add(resultado3);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-06"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }

    @Test
    public void filtrarDesde04_01_2020Hasta06_01_2020() {
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-04"), LocalDate.parse("2020-01-06")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }

    @Test
    public void filtrarDesde05_01_2020Hasta10_01_2020() {
        this.resultadosEsperados.add(resultado2);
        this.resultadosEsperados.add(resultado3);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-05"), LocalDate.parse("2020-01-10")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }

    @Test
    public void filtrarDesde01_01_2020Hasta05_01_2020() {
        this.resultadosEsperados.add(resultado1);
        this.resultadosEsperados.add(resultado2);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-05")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosEsperados, resultadosFiltrados);
    }

    @Test
    public void filtrarDesde01_01_2019Hasta01_01_2020() {
        this.resultadosEsperados.add(resultado1);
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }

    @Test
    public void filtrarDesde01_01_2019Hasta31_12_2019() {
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }

    @Test
    public void filtrarDesde11_01_2020Hasta31_12_2022() {
        this.bandeja.agregarFiltro(new FiltroFecha(LocalDate.parse("2020-01-11"), LocalDate.parse("2022-01-12")));
        this.resultadosFiltrados = bandeja.filtrarResultados();

        Assert.assertEquals(resultadosFiltrados, resultadosEsperados);
    }
}