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
import domain.entities.usuario.Usuario;
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

    private ProveedorDAO proveedorDAO = new ProveedorDAOMySQL();
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMySQL();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();
    private MedioDePagoDAO medioDePagoDAO = new MedioDePagoDAOMySQL();
    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOMySQL();



    public ModelAndView nuevoEgreso(Request request, Response response) throws Exception {
        //TODO: en la vista, para el medio de pago, se le puede poner numero

        //String usuarioID = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<MedioDePago> medioDePagoList = this.medioDePagoDAO.buscarTodosLosMediosDePago();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("mediosDePago", medioDePagoList);
        parametros.put("tiposCombantes", tipoComprobanteList);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {
        String usuarioIDSpark = request.session().attribute("id");

        Proveedor proveedor = proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId")));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        Integer cantidadPresupuestos = Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos"));
        Organizacion organizacion = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark).getRol().getOrganizacion();
        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        List<CategoriaDeOperaciones> categoriasDeOperaciones = this.getListaDeCategorias(request);
        MedioDePago medioDePago = this.medioDePagoDAO.buscarMedioDePagoPorId(new Integer(request.queryParams("medioDePagoIdDB")));

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setProveedor(proveedor);
        builder.setCantEsperadaPresupuestos(cantidadPresupuestos);
        builder.setFecha(fecha);
        builder.setNumeroIdentificadorMedioPago(request.queryParams("numeroIdentificadorDelMedio"));
        builder.setMedioDePago(medioDePago);
        builder.setOrganizacion(organizacion);
        builder.setDetalle(detallesEgresos);
        builder.setCategoriasAsociadas(categoriasDeOperaciones);
        OperacionEgreso operacion = builder.build();

        this.operacionEgresoDAO.guardarOperacionEgreso(operacion);

        response.redirect("/operacionesEgreso");
        return response;
    }


    public ModelAndView editarEgreso(Request request, Response response) throws Exception {
        //TODO: revisar edicion de cada uno de los campos
        //TODO: que no se pueda guardar si no tenes ningun detalle de egreso
        //String usuarioID = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Integer id = new Integer(request.queryParams("egresoId"));
        OperacionEgreso operacionEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(id);
        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<MedioDePago> medioDePagoList = this.medioDePagoDAO.buscarTodosLosMediosDePago();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("mediosDePago", medioDePagoList);
        parametros.put("tiposCombantes", tipoComprobanteList);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("egreso", operacionEgreso);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardarEditarEgreso(Request request, Response response) throws Exception {

        OperacionEgreso egreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(new Integer(request.queryParams("egresoId")));

        egreso.setFecha(LocalDate.parse(request.queryParams("fecha")));
        egreso.setCantidadEsperadaPresupuestos(Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos")));
        egreso.setProveedor(proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId"))));

        egreso.getMedioDePago().setDescMercadoPago(request.queryParams("descripcionDelPago"));
        egreso.getMedioDePago().setIdMercadoPago(request.queryParams("medioDePagoId"));
        egreso.getMedioDePago().setTipoMercadoPago(request.queryParams("tipoDePago"));

        egreso.getDocumentoComercial().setNumeroDocumentoComercial(new Long(request.queryParams("documentoComercialNumero")));
        egreso.getDocumentoComercial().getTipoDocumentoComercial().setDescripcion(request.queryParams("documentoComercialClase"));

        this.removerCategoriasDeEgreso(egreso, request);
        List<CategoriaDeOperaciones> categoriasAgregadas = this.getListaDeCategorias(request);
        categoriasAgregadas.forEach( categoria -> egreso.asociarACategoria(categoria));

        //TODO: agregar el boton de borrar a los items nuevos
        this.actualizarDetallesDeEgreso(egreso,request);
        egreso.registrarDetalles(this.getListaDeDetalle(request));

        this.operacionEgresoDAO.modificarOperacionEgreso(egreso);

        response.redirect("/operacionesEgreso");
        return response;
    }

    private void actualizarDetallesDeEgreso(OperacionEgreso egreso, Request request) {
        List<DetalleEgreso> detallesAEliminar = new ArrayList<>();

        egreso.getDetalle().forEach( detalleEgreso -> {
            try {
                detalleEgreso.setValorTotal(new Double(request.queryParams("valorItemExistenteId" + detalleEgreso.getId())));
                detalleEgreso.setCantidad(new Integer(request.queryParams("valorItemExistenteId" + detalleEgreso.getId())));
                detalleEgreso.getItem().setDescripcion((request.queryParams("valorItemExistenteId" + detalleEgreso.getId())));
            } catch (Exception e) {
                detallesAEliminar.add(detalleEgreso);
            }
        });

        egreso.quitarDetalles(detallesAEliminar);
    }

    private void removerCategoriasDeEgreso(OperacionEgreso egreso, Request request) {
        try {
            Integer cantidadCategoriasARemover = new Integer(request.queryParams("categoriasExistentesEliminadas"));
            IntStream.range(0,cantidadCategoriasARemover).forEach( i -> {
                try {
                    int idDeLaCategoriaARemover = new Integer(request.queryParams("categoriaBorradaId" + i));
                    egreso.quitarCategoriaPorId(idDeLaCategoriaARemover);

                } catch (Exception e) {}
            });

        } catch (Exception e) {}



    }


    private List<CategoriaDeOperaciones> getListaDeCategorias(Request request) {

        List<CategoriaDeOperaciones> categorias = new ArrayList<>();

        try {
            System.out.println("Id de la categoria en FE: " + request.queryParams("cantidadDeCategoriasNuevas"));
            Integer cantidadDeCategorias = new Integer(request.queryParams("cantidadDeCategoriasNuevas"));

            IntStream.range(0,cantidadDeCategorias).forEach( i -> {

                try {
                    int idDeLaCategoria = new Integer(request.queryParams("categoriaNuevaId" + i));

                    System.out.println("Id de la categoria en DB: " + idDeLaCategoria);
                    CategoriaDeOperaciones categoria = this.categoriaDAO.buscarCategoriaPorId(idDeLaCategoria);

                    System.out.println("Descripcion categoria: " + categoria.getDescripcion());
                    categorias.add(categoria);
                } catch (Exception e) {}
            });

        } catch (Exception e) {}

        return  categorias;
    }

    private List<DetalleEgreso> getListaDeDetalle(Request request) {
        List<DetalleEgreso> detallesEgresos = new ArrayList<>();

        try {
            Integer cantidadDeEgresos = new Integer(request.queryParams("cantidadDetalles"));
            IntStream.range(0,cantidadDeEgresos).forEach( i -> {
                try {
                    DetalleEgreso detalleEgreso = new DetalleEgreso();
                    detalleEgreso.setValorTotal(new Double(request.queryParams("valorItem" + i)));
                    detalleEgreso.setCantidad(new Integer(request.queryParams("cantidadItem" + i)));

                    Item item = new Item();
                    item.setDescripcion(request.queryParams("descripcionItem" + i));
                    detalleEgreso.setItem(item);

                    detallesEgresos.add(detalleEgreso);
                } catch ( Exception e) {}

            });
        } catch (Exception e) {}


        return  detallesEgresos;
    }

    public Response guardarDocumentoComercial(Request request, Response response) throws Exception {

        System.out.println("---- EGRESOID: " + request.queryParams("egresoId"));
        System.out.println("---- TIPO DOCUMENTO: " + request.queryParams("tipoDocumento"));
        System.out.println("---- NUMERO: " + request.queryParams("numero"));
        System.out.println("---- TIPO COMPROBANTE ID: " + request.queryParams("tipoComprobanteId"));
        System.out.println("---- CONTENIDO: " + request.queryParams("contenidoSerializado"));


        TipoComprobante tipoComprobante = new TipoComprobanteDAOMySQL().buscarTipoComprobantePorId(new Integer(request.queryParams("tipoComprobanteId")));

        DocumentoComercial documentoComercial = new DocumentoComercial();
        documentoComercial.setNumeroDocumentoComercial(new Long(request.queryParams("numero")));
        documentoComercial.setTipoDocumentoComercial(tipoComprobante);
        documentoComercial.setActivo(true);
        switch (request.queryParams("tipoDocumento")) {
            case "Fisico":
                documentoComercial.setTipoDeAdjunto("Fisico");
                documentoComercial.setContent(null);
                break;
            case "Digital":
                documentoComercial.setTipoDeAdjunto("Digital");
                documentoComercial.setContent(request.queryParams("contenidoSerializado").getBytes());
        }

        OperacionEgreso egreso = new OperacionEgresoDAOMySQL().buscarOperacionEgresoPorId(new Integer(request.queryParams("egresoId")));
        egreso.setDocumentoComercial(documentoComercial);

        this.operacionEgresoDAO.modificarOperacionEgreso(egreso);

        return response;
    }
}
