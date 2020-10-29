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

    private ProveedorDAO proveedorDAO = new ProveedorDAOMemoria();
    private OrganizacionDAO organizacionDao = new OrganizacionDAOMemoria();
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMemoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMemoria();
    private UserDAO userDAO = new UserDAOMySQL();



    public ModelAndView editarEgreso(Request request, Response response) throws Exception {
        //TODO: ordenar el controller y poner primero los metodos de egreso nuevo, dividirlo en 2 controllers
        Integer id = new Integer(request.queryParams("egresoId"));
        OperacionEgreso operacionEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(id);

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", request.queryParams("usuarioId"));
        parametros.put("egreso", operacionEgreso);

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

        //TODO: revisar documento comercial que es tipo y que es clase
        egreso.getDocumentoComercial().setNumeroDocumentoComercial(new Long(request.queryParams("documentoComercialNumero")));
        egreso.getDocumentoComercial().getTipoDocumentoComercial().setDescripcion(request.queryParams("documentoComercialClase"));

        this.removerCategoriasDeEgreso(egreso, request);
        List<CategoriaDeOperaciones> categoriasAgregadas = this.getListaDeCategorias(request);
        categoriasAgregadas.forEach( categoria -> egreso.asociarACategoria(categoria));

        //TODO: agregar el boton de borrar a los items nuevos
        this.actualizarDetallesDeEgreso(egreso,request);
        egreso.registrarDetalles(this.getListaDeDetalle(request));

        //TODO: DAO.guardarEdicion(egreso)

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

    private void actualizarDetallesDeEgreso(OperacionEgreso egreso, Request request) {
        //TODO: checkear que valla al catch cuando borre un detalle
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
                    //TODO: debuguear para probar este metodo
                    egreso.quitarCategoriaPorId(idDeLaCategoriaARemover);

                } catch (Exception e) {}
            });

        } catch (Exception e) {}



    }

    public ModelAndView nuevoEgreso(Request request, Response response) throws Exception {

            //TODO: los medios de pago son genericos o son de cada organizacion?
            //TODO: validar que no haya dos ingresos asociados al mismo egreso
            //TODO: para el tipo le tengo que enviar los de ML
            //TODO: validacion por FE para no hacer guardar con campos vacios
            //TODO: borrar categorias he items
            //TODO: no poder agregar la misma categoria dos veces.
            //TODO: en la vista, cuando el documento es fisico no tengo que mostrar el campo de archivo adjunto
            //TODO: boton cancelar para volver a la vista anterior

            List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
            List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("provedoores", proveedores);
            parametros.put("categorias", categorias);
            parametros.put("usuarioId", request.queryParams("usuarioId"));

            return new ModelAndView( parametros, "operacionEgresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {
        //TODO: verificar las categorias anden
        //TODO: categorias borradas cuidado
        Proveedor proveedor = proveedorDAO.getProveedor(new Integer(request.queryParams("proveedorId")));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        DocumentoComercial documentoComercial = this.crearDocumentoComercial(request);
        Integer cantidadPresupuestos = Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos"));
        Organizacion organizacion = organizacionDao.getOrganizacionPorUsuarioId(new Integer(request.queryParams("usuarioId")));
        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        List<CategoriaDeOperaciones> categoriasDeOperaciones = this.getListaDeCategorias(request);

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
        builder.setCategoriasAsociadas(categoriasDeOperaciones);
        OperacionEgreso operacion = builder.build();

        this.operacionEgresoDAO.guardarOperacionEgreso(operacion);

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

    private List<CategoriaDeOperaciones> getListaDeCategorias(Request request) {

        List<CategoriaDeOperaciones> categorias = new ArrayList<>();

        try {
            Integer cantidadDeCategorias = new Integer(request.queryParams("cantidadDeCategoriasNuevas"));
            IntStream.range(0,cantidadDeCategorias).forEach( i -> {

                try {
                    int idDeLaCategoria = new Integer(request.queryParams("categoriaNuevaId" + i));
                    CategoriaDeOperaciones categoria = this.categoriaDAO.buscarCategoriaPorId(idDeLaCategoria);
                    categorias.add(categoria);
                } catch (Exception e) {}
            });

        } catch (Exception e) {}

        return  categorias;
    }

    private List<DetalleEgreso> getListaDeDetalle(Request request) {
        //TODO: controlar que la cantidad de egresos no sea nula, va en la vista
        //TODO: eliminar detalles
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
            	TipoComprobante tipoComprobanteDigital = new TipoComprobante();
            	tipoComprobanteDigital.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumentoDigital = new Long(request.queryParams("documentoComercialNumero"));
                documentoComercial.altaDocumentoComercial(tipoComprobanteDigital, numeroDocumentoDigital,"Digital",request.queryParams("documentoComercialAdjunto"));
                        
                break;
        }

        return documentoComercial;
    }
}
