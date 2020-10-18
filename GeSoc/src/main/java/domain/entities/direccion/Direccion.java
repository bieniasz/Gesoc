package domain.entities.direccion;

import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class Direccion extends EntidadPersistente{

    @Column
    public String calle;

    @Column
    public String altura;

    @ManyToOne
    public Ciudad ciudad;

    @ManyToOne
    public Provincia provincia;

    @ManyToOne
    public Pais pais;

    @Column
    private boolean activo;

    @OneToOne
    public Moneda monedaLocal;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Moneda getMonedaLocal() {
        return monedaLocal;
    }

    public void setMonedaLocal(Moneda monedaLocal) {
        this.monedaLocal = monedaLocal;
    }
}