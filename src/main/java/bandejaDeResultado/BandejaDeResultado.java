package bandejaDeResultado;

import bandejaDeResultado.filtroDeResultado.FiltroDeResultado;
import validadorTransparencia.ResultadoDeValidacion;


import java.util.ArrayList;
import java.util.List;

public class BandejaDeResultado {

    private List<ResultadoDeValidacion> resultadosDeValidacion;
    private List<FiltroDeResultado> filtrosDeResultados;


    public BandejaDeResultado() {
        this.resultadosDeValidacion = new ArrayList<>();
        this.filtrosDeResultados = new ArrayList<>();
    }

    public void recibirResultado(ResultadoDeValidacion resultadoDeValidacion){
        resultadosDeValidacion.add(resultadoDeValidacion);
    }

    public Boolean leerResultado(ResultadoDeValidacion resultadoDeValidacion){
        return resultadoDeValidacion.leido(); //leído es un bool finalmente?
    }

    public void agregarFiltro(FiltroDeResultado unFiltro){
        filtrosDeResultados.add(unFiltro);
    }

    public void vaciarFiltros(){
        filtrosDeResultados.clear();
    }

    public List<ResultadoDeValidacion> filtrarResultados(List<FiltroDeResultado> filtros){

        List<ResultadoDeValidacion> resultadosFiltrados;

        resultadosFiltrados = this.resultadosDeValidacion;

        for(FiltroDeResultado unFiltro : filtros)
        {
            resultadosFiltrados = unFiltro.filtrar(resultadosFiltrados);
        }
        return resultadosFiltrados;
    }
}