package domain.entities.usuario;

import domain.entities.bandejaDeResultado.BandejaDeResultado;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.organizacion.Organizacion;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import javax.persistence.*;


@Entity
@DiscriminatorValue("Revisor")
public class UsuarioRevisor extends Rol{

    @ManyToOne
    private Organizacion organizacion;

    @OneToOne
    public BandejaDeResultado bandejaDeResultado = new BandejaDeResultado();

    public UsuarioRevisor(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public void crearRevisionDeCompra(OperacionEgreso operacionEgreso){
        //Por el momento este metodo no es usado, aca se creara la suscripcion a la revision de la compra para persistirla
    }

    @Override
    public Organizacion altaOrganizacionJuridica() {
        return null;
    }

    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) {
        return null;
    }

    @Override
    public Organizacion getOrganizacion() {
        return null;
    }

    public void recibirResultado(ResultadoDeValidacion resultadoDeValidacion) {
        this.bandejaDeResultado.recibirResultado(resultadoDeValidacion);
    }
}
