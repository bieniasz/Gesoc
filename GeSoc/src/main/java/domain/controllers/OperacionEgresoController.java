package domain.controllers;

import db.DAOs.*;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.*;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import domain.entities.organizacion.EntidadJuridica;
import domain.entities.organizacion.Organizacion;
import domain.entities.organizacion.categoria.Categoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.stream.Stream;

public class OperacionEgresoController {

    private ProveedorDAO proveedorDAO = new ProveedorDAOMemoria();
    private OrganizacionDAO organizacionDao = new OrganizacionDAOMemoria();
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMemoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMemoria();


    public ModelAndView nuevoEgreso(Request request, Response response) throws Exception {


            //TODO: los medios de pago son genericos o son de cada organizacion?
            //TODO traerme todas las categorias de la empresa del flaco
            //TODO: las categorias de cada organizacion o generales de la plataforma?
            //TODO: alidar que no haya dos ingresos asociados al mismo egreso
            //TODO: para el tipo le tengo que enviar los de ML
            //TODO: validacion por FE para no hacer guardar con campos vacios
            //TODO: borrar categorias he items
            //TODO: no poder agregar la misma categoria dos veces.

            //TODO: en la vista, cuando el documento es fisico no tengo que mostrar el campo de archivo adjunto


            List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
            List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("provedoores", proveedores);
            parametros.put("categorias", categorias);
            parametros.put("usuarioId", request.queryParams("usuarioId"));

            return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {

        //TODO: categorias, redireccionar a la vista principal
        //TODO: verificar las categorias anden
        Proveedor proveedor = proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId")));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        DocumentoComercial documentoComercial = this.crearDocumentoComercial(request);
        Integer cantidadPresupuestos = Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos"));
        Organizacion organizacion = organizacionDao.getOrganizacionPorUsuarioId(new Integer(request.queryParams("usuarioId")));

        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        MedioDePago medioDePago = new MedioDePago();
        medioDePago.setDescMercadoPago(request.queryParams("descripcionDelPago"));
        medioDePago.setIdMercadoPago(request.queryParams("medioDePagoId"));
        medioDePago.setTipoMercadoPago(request.queryParams("tipoDePago"));

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setProveedor(proveedor);
        builder.setCantEsperadaPresupuestos(cantidadPresupuestos);
        builder.setFecha(fecha);
        builder.setNumeroIdentificadorMedioPago(request.queryParams("medioDePagoId"));
        builder.setDocumentoComercial(documentoComercial);
        builder.setOrganizacion(organizacion);
        builder.setDetalle(detallesEgresos);
        builder.setMedioDePago(new MedioDePago());
        OperacionEgreso operacion = builder.build();

        this.operacionEgresoDAO.guardarOperacionEgreso(operacion);

        ///egreso?usuarioId=5
        response.redirect("/operaciones?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

    private List<DetalleEgreso> getListaDeDetalle(Request request) {
        //TODO: controlar que la cantidad de egresos no sea nula, va en la vista
        Integer cantidadDeEgresos = new Integer(request.queryParams("cantidadDetalles"));

        List<DetalleEgreso> detallesEgresos = new ArrayList<>();
        IntStream.range(0,cantidadDeEgresos).forEach( i -> {

            DetalleEgreso detalleEgreso = new DetalleEgreso();
            detalleEgreso.setValorTotal(new Double(request.queryParams("valorItem" + i)));
            detalleEgreso.setCantidad(new Integer(request.queryParams("cantidadItem" + i)));

            Item item = new Item();
            item.setDescripcion(request.queryParams("descripcionItem" + i));
            detalleEgreso.setItem(item);

            detallesEgresos.add(detalleEgreso);
        });

        return  detallesEgresos;
    }

    private DocumentoComercial crearDocumentoComercial(Request request) {
        DocumentoComercial documentoComercial = new DocumentoComercial();

        switch (request.queryParams("documentoComercialTipo")) {
            case "Fisico":
                TipoComprobante tipoComprobante = new TipoComprobante();
                tipoComprobante.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumento = new Long(request.queryParams("documentoComercialNumero"));
                documentoComercial.guardarDocumentoFisico(tipoComprobante, numeroDocumento,null);
                documentoComercial.setContent(request.queryParams("documentoComercialAdjunto"));
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
}
