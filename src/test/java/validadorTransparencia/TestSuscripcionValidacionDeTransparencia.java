package validadorTransparencia;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.EntidadBase;
import usuario.UsuarioRevisor;
import validacionEgresos.CriterioValidacionEgresosPresupuesto;
import validacionEgresos.criterios.CantidadIndicada;
import validacionEgresos.criterios.MenorValor;

public class TestSuscripcionValidacionDeTransparencia {

    private SuscripcionValidacionDeTransparencia suscripcion;
    private UsuarioRevisor revisor1;
    private OperacionEgreso operacionEgreso;

    @Before
    public void init(){
        this.operacionEgreso = new OperacionEgreso();
        Presupuesto presupuesto = new Presupuesto();
        DetalleEgreso det = new DetalleEgreso();
        det.valorTotal = Double.valueOf(2);
        presupuesto.registrarDetalle(det);
        presupuesto.setEsElElegido(true);
        this.operacionEgreso.asociarPresupuesto(presupuesto);

        CantidadIndicada criterioCantidad = new CantidadIndicada();
        criterioCantidad.setCantidadPresupuestos(1);
        this.revisor1 = new UsuarioRevisor(new EntidadBase());

        this.suscripcion = new SuscripcionValidacionDeTransparencia();
        this.suscripcion.agregarCriterio(criterioCantidad);
        this.suscripcion.setOperacionEgreso(this.operacionEgreso);
        this.suscripcion.agregarRevisor(revisor1);
    }

    @Test
    public void TestSuscripcionGeneraUnUnicoResultado() {
        this.suscripcion.validar();
        int cantidadDeResultados = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().size();
        Assert.assertEquals(1, cantidadDeResultados);
    }

    @Test
    public void TestSuscripcionValidaGeneraUnMensaje() {
        this.suscripcion.validar();
        int cantidadDeMensajes = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().size();
        Assert.assertEquals(1, cantidadDeMensajes);
    }

    @Test
    public void TestSuscripcionValidaMensaje() {
        this.suscripcion.validar();
        String mensajeEnResultadoDeValidacion = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().get(0);
        Assert.assertEquals("La Operacion de Egreso cumple con todas las politicas",mensajeEnResultadoDeValidacion);
    }

    @Test
    public void TestSuscripcionConErrorGenera1SoloResultado() {
        Presupuesto otroPresupuesto = new Presupuesto();
        MenorValor criterioMenorValor = new MenorValor();
        this.suscripcion.agregarCriterio(criterioMenorValor);
        this.suscripcion.validar();

        int cantidadDeResultados = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().size();
        Assert.assertEquals(1, cantidadDeResultados);
    }

    @Test
    public void TestSuscripcionConErrorGeneraCantidadDeMensajes() {
        Presupuesto otroPresupuesto = new Presupuesto();
        DetalleEgreso detalle = new DetalleEgreso();
        detalle.valorTotal = Double.valueOf(1);
        otroPresupuesto.registrarDetalle(detalle);

        MenorValor criterioMenorValor = new MenorValor();
        this.suscripcion.agregarCriterio(criterioMenorValor);
        this.suscripcion.validar();

        int cantidadDeMensajes = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().size();
        Assert.assertEquals(1, cantidadDeMensajes);
    }

    @Test
    public void TestSuscripcionConErrorMensaje() {
        Presupuesto otroPresupuesto = new Presupuesto();
        DetalleEgreso det = new DetalleEgreso();
        det.valorTotal = Double.valueOf(1);
        otroPresupuesto.registrarDetalle(det);
        this.operacionEgreso.asociarPresupuesto(otroPresupuesto);

        MenorValor criterioMenorValor = new MenorValor();
        this.suscripcion.agregarCriterio(criterioMenorValor);
        this.suscripcion.validar();

        String mensaje = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().get(0);

        Assert.assertEquals("No coincide el presupuesto elegido con el de menor valor", mensaje);
    }

    @Test
    public void TestSuscripcionNotificaAMultiplesRevisores() {
        UsuarioRevisor revisor2 = new UsuarioRevisor(new EntidadBase());
        this.suscripcion.agregarRevisor(revisor2);

        Presupuesto otroPresupuesto = new Presupuesto();
        MenorValor criterioMenorValor = new MenorValor();
        this.suscripcion.agregarCriterio(criterioMenorValor);
        this.suscripcion.validar();

        String mensaje1 = this.revisor1.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().get(0);
        String mensaje2 = revisor2.bandejaDeResultado.getResultadosDeValidacion().get(0).getResultados().get(0);

        Assert.assertEquals(mensaje1, mensaje2);
    }
}
