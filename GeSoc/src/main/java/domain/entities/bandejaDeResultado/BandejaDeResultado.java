package domain.entities.bandejaDeResultado;

import domain.entities.bandejaDeResultado.filtroDeResultado.FiltroDeResultado;
import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class BandejaDeResultado extends EntidadPersistente {

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="BandejaDeResultado",referencedColumnName = "id")
    private List<ResultadoDeValidacion> resultadosDeValidacion;

    @Transient
    private List<FiltroDeResultado> filtrosDeResultados;


    public BandejaDeResultado() {
        this.resultadosDeValidacion = new ArrayList<>();
        this.filtrosDeResultados = new ArrayList<>();
    }

    public void recibirResultado(ResultadoDeValidacion resultadoDeValidacion){
        resultadosDeValidacion.add(resultadoDeValidacion);
    }

    public void leerResultado(ResultadoDeValidacion resultadoDeValidacion){
        resultadoDeValidacion.marcarLeido();
    }

    public void agregarFiltro(FiltroDeResultado unFiltro){
        filtrosDeResultados.add(unFiltro);
    }

    public void vaciarFiltros(){
        filtrosDeResultados.clear();
    }

    public List<ResultadoDeValidacion> filtrarResultados(){

        List<ResultadoDeValidacion> resultadosFiltrados;
        resultadosFiltrados = this.resultadosDeValidacion;

        for(FiltroDeResultado unFiltro : this.filtrosDeResultados)
        {
            resultadosFiltrados = unFiltro.filtrar(resultadosFiltrados);
        }
        return resultadosFiltrados;
    }

    public List<ResultadoDeValidacion> getResultadosDeValidacion() {
        return resultadosDeValidacion;
    }

    public List<FiltroDeResultado> getFiltrosDeResultados() {
        return filtrosDeResultados;
    }

    public void setResultadosDeValidacion(ResultadoDeValidacion resultadosDeValidacion) {
        this.resultadosDeValidacion.add(resultadosDeValidacion);
    }

    public void setFiltrosDeResultados(FiltroDeResultado filtrosDeResultados) {
        this.filtrosDeResultados.add(filtrosDeResultados);
    }
}