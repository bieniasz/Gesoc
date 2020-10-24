package domain.controllers;

import db.DAOs.*;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.DetalleEgreso;
import domain.entities.operacionComercial.Item;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import domain.entities.organizacion.EntidadJuridica;
import domain.entities.organizacion.Organizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionEgresoController {

    private ProveedorDAO proveedorDAO = new ProveedorDAOMemoria();
    private OrganizacionDAO organizacionDao = new OrganizacionDAOMemoria();

    public String saluda(Request request, Response response) {
        return "Saludos humano";
    }

    public ModelAndView crear(Request request, Response response) throws Exception {

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        //TODO: los medios de pago son genericos o son de cada organizacion?
        //TODO traerme todas las categorias de la empresa del flaco
        //TODO: las categorias de cada organizacion o generales de la plataforma?
        //TODO: dos operaciones pueden estar asociadas al mismo presupuesto?
        //List<Categorias>
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("provedoores", proveedores);

        return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public ModelAndView guardar(Request request, Response response) throws Exception {

        //TODO: para los detalles de egreso puedo agarrar toda la tabla por javascript, parsearla en un json y recibirla de este lado en el request.body()

        Proveedor proveedor = proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId")));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        DocumentoComercial documentoComercial = this.crearDocumentoComercial(request);
        Integer cantidadPresupuestos = Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos"));
        Organizacion organizacion = organizacionDao.getOrganizacionPorUsuarioId(new Integer(request.queryParams("usuarioId")));

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setProveedor(proveedor);
        builder.setCantEsperadaPresupuestos(cantidadPresupuestos);
        builder.setFecha(fecha);
        builder.setNumeroIdentificadorMedioPago(request.queryParams("numeroIdentificadorMedioPago"));
        builder.setDocumentoComercial(documentoComercial);
        builder.setOrganizacion(organizacion);
        /*
        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        */
        //OperacionEgreso operacion = builder.build();

        return new ModelAndView(null, "operacionEgresoNuevo.hbs");
    }

    private DocumentoComercial crearDocumentoComercial(Request request) {
        DocumentoComercial documentoComercial = new DocumentoComercial();

        switch (request.queryParams("documentoComercialTipo")) {
            case "Fisico":
                TipoComprobante tipoComprobante = new TipoComprobante();
                tipoComprobante.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumento = new Long(request.params("documentoComercialNumero"));
                documentoComercial.guardarDocumentoFisico(tipoComprobante, numeroDocumento,null);
                break;
            case "Digital":
                //TODO: completar
                //documento.altaDocumentoComercial();
                break;
        }

        return documentoComercial;
    }

    public ModelAndView mostrarEgresos(Request request, Response response) throws Exception {
        /*DetalleEgreso unDetalle = new DetalleEgreso();
        Item item = new Item();
        item.setDescripcion("Coca");
        unDetalle.setItem(item);
        unDetalle.valorTotal = 5.0;
        unDetalle.cantidad = 2;

        DetalleEgreso otroDetalle = new DetalleEgreso();
        Item otroitem = new Item();
        otroitem.setDescripcion("Papitas");
        otroDetalle.valorTotal = 6.0;
        unDetalle.cantidad = 3;

        List<DetalleEgreso> detalles = new ArrayList<>();
        detalles.add(unDetalle);
        detalles.add(otroDetalle);

        EntidadJuridica organizacion = new EntidadJuridica();
        organizacion.setNombreFicticio("Alfombritas SRL");

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setDetalle(detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(organizacion);
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();


        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombreApellido_RazonSocial("Alfred SA");
        proveedor1.setId(1);
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setNombreApellido_RazonSocial("Doritos SRL");
        proveedor2.setId(2);
        this.proveedores.add(proveedor1);
        this.proveedores.add(proveedor2);

        //TODO: guardar en parametros la lista completa de proveedores
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("operacion", operacion);
        parametros.put("provedoores", this.proveedores);*/

        return new ModelAndView(null, "operacionEgresoNuevo.hbs");
    }



    public Response guardarItem(Request request, Response response) {
        Item item = new Item();
        item.setDescripcion(request.queryParams("descripcion"));

        DetalleEgreso detalleNuevo = new DetalleEgreso();
        detalleNuevo.setItem(item);
        detalleNuevo.setCantidad(new Integer(request.queryParams("cantidad")));
        detalleNuevo.setValorTotal(new Double(request.queryParams("valor")));

        return  response;
    }
}
