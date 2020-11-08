package domain.entities.operacionComercial;

//import jdk.vm.ci.meta.Local;

import domain.entities.organizacion.Organizacion;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class OperacionIngreso extends EntidadPersistente {

    public OperacionIngreso() {
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "organizacion",referencedColumnName = "id")
    private Organizacion organizacion;

    @Column
    private String descripcion;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaHastaAceptable;

    @Column
    private Float monto;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<OperacionEgreso> egresosAsociados;

    public void agregarEgresoAsociado(OperacionEgreso egreso) { this.egresosAsociados.add(egreso); }

    /* GETTERS & SETTERS */
    public String getDescripcion() {
        return descripcion;
    }
    public Float getMonto() {
        return monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Organizacion getOrganizacion() { return organizacion; }
    public List<OperacionEgreso> getEgresosAsociados() { return egresosAsociados; }
    public LocalDate getFechaHastaAceptable() { return this.fechaHastaAceptable; }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setMonto(Float monto) {
        this.monto = monto;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }
    public void setEgresosAsociados(List<OperacionEgreso> egresosAsociados) { this.egresosAsociados = egresosAsociados; }
    public void setFechaHastaAceptable(LocalDate fecha) { this.fechaHastaAceptable = fecha; }
}
