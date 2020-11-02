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



    public ModelAndView nuevoEgreso(Request request, Response response) throws Exception {
        //TODO: en la vista, para el medio de pago, se le puede poner numero

        String usuarioID = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioID);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<MedioDePago> medioDePagoList = this.medioDePagoDAO.buscarTodosLosMediosDePago();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("mediosDePago", medioDePagoList);
        parametros.put("usuarioId", request.queryParams("usuarioId"));
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {
        Proveedor proveedor = proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId")));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        DocumentoComercial documentoComercial = this.crearDocumentoComercial(request);
        Integer cantidadPresupuestos = Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos"));
        Organizacion organizacion = this.userDAO.buscarUsuarioPoruserId(request.queryParams("usuarioId")).getRol().getOrganizacion();
        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        List<CategoriaDeOperaciones> categoriasDeOperaciones = this.getListaDeCategorias(request);
        MedioDePago medioDePago = this.medioDePagoDAO.buscarMedioDePagoPorId(new Integer(request.queryParams("medioDePagoIdDB")));

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setProveedor(proveedor);
        builder.setCantEsperadaPresupuestos(cantidadPresupuestos);
        builder.setFecha(fecha);
        builder.setNumeroIdentificadorMedioPago(request.queryParams("numeroIdentificadorDelMedio"));
        builder.setMedioDePago(medioDePago);
        builder.setDocumentoComercial(documentoComercial);
        builder.setOrganizacion(organizacion);
        builder.setDetalle(detallesEgresos);
        builder.setCategoriasAsociadas(categoriasDeOperaciones);
        OperacionEgreso operacion = builder.build();

        this.operacionEgresoDAO.guardarOperacionEgreso(operacion);

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }


    public ModelAndView editarEgreso(Request request, Response response) throws Exception {
        String usuarioID = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioID);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Integer id = new Integer(request.queryParams("egresoId"));
        OperacionEgreso operacionEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(id);

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", request.queryParams("usuarioId"));
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

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
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
            	TipoComprobante tipoComprobanteDigital = new TipoComprobante();
            	tipoComprobanteDigital.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumentoDigital = new Long(request.queryParams("documentoComercialNumero"));
                documentoComercial.altaDocumentoComercial(tipoComprobanteDigital, numeroDocumentoDigital,"Digital",request.queryParams("documentoComercialAdjunto"));
                        
                break;
        }

        return documentoComercial;
    }
}
