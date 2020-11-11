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
    private UsuarioHandler usuarioHandler = new UsuarioHandler();



    public ModelAndView nuevoEgreso(Request request, Response response) throws Exception {
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<MedioDePago> medioDePagoList = this.medioDePagoDAO.buscarTodosLosMediosDePago();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("mediosDePago", medioDePagoList);
        parametros.put("tiposCombantes", tipoComprobanteList);


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
        //TODO: que no se pueda guardar si no tenes ningun detalle de egreso
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Integer id = new Integer(request.queryParams("egresoId"));
        OperacionEgreso operacionEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(id);
        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<MedioDePago> medioDePagoList = this.medioDePagoDAO.buscarTodosLosMediosDePago();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("mediosDePago", medioDePagoList);
        parametros.put("tiposCombantes", tipoComprobanteList);
        parametros.put("egreso", operacionEgreso);


        return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardarEditarEgreso(Request request, Response response) throws Exception {

        OperacionEgreso egreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(new Integer(request.queryParams("egresoId")));

        egreso.setFecha(LocalDate.parse(request.queryParams("fecha")));
        egreso.setCantidadEsperadaPresupuestos(Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos")));
        egreso.setProveedor(proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId"))));

        egreso.setNumeroIdentificadorMedioPago(request.queryParams("numeroIdentificadorDelMedio"));
        MedioDePago medioDePago = this.medioDePagoDAO.buscarMedioDePagoPorId(new Integer(request.queryParams("medioDePagoIdDB")));
        egreso.setMedioDePago(medioDePago);

        this.removerCategoriasDeEgreso(egreso, request);
        List<CategoriaDeOperaciones> categoriasAgregadas = this.getListaDeCategorias(request);
        egreso.getCategoriasAsociadas().addAll(categoriasAgregadas);
        egreso.getCategoriasAsociadas().forEach( categoria -> categoria.agregarOperacion(egreso));

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
                detalleEgreso.setValorTotal(new Double(request.queryParams("valorItemExistenteValor" + detalleEgreso.getId())));
                detalleEgreso.setCantidad(new Integer(request.queryParams("valorItemExistenteCantidad" + detalleEgreso.getId())));
                detalleEgreso.getItem().setDescripcion((request.queryParams("valorItemExistenteDescripcion" + detalleEgreso.getId())));
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
        System.out.println("Cantidad de categorias agregadas: " + request.queryParams("cantidadDeCategoriasNuevas"));
        Integer cantidadDeCategorias = new Integer(request.queryParams("cantidadDeCategoriasNuevas"));

        try {
            IntStream.range(0,cantidadDeCategorias).forEach( i -> {

                int idDeLaCategoria = new Integer(request.queryParams("categoriaNuevaId" + i));
                System.out.println("Id de la categoria en DB: " + idDeLaCategoria);

                try {

                    CategoriaDeOperaciones categoria = this.categoriaDAO.buscarCategoriaPorId(idDeLaCategoria);

                    System.out.println("Descripcion categoria: " + categoria.getDescripcion());
                    categorias.add(categoria);
                } catch (Exception e) {}
            });

        } catch (Exception e) {}

        System.out.println("LONGITUD LISTA CATEGORIAS: " + categorias.size());
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

        //TODO: guardar documento editado
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
