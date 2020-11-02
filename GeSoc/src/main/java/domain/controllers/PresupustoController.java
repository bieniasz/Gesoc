package domain.controllers;

import db.DAOs.*;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.*;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import domain.entities.operacionComercial.builder.PresupuestoBuilder;
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

public class PresupustoController {

    private ProveedorDAO proveedorDAO = new ProveedorDAOMemoria();
    private OrganizacionDAO organizacionDao = new OrganizacionDAOMemoria();
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMemoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMemoria();
    private PresupuestoDAO presupuestoDAO = new PresupuestoDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();



    public ModelAndView editarPresupuesto(Request request, Response response) throws Exception {

        String usuarioID = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioID);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Integer id = new Integer(request.queryParams("presupuestoId"));

        Presupuesto presupueto = this.presupuestoDAO.buscarPresupuesto(id);

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", request.queryParams("usuarioId"));
        //parametros.put("egreso", operacionEgreso);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "Presupuesto.hbs");
    }

    public Response guardarEditarPresupuesto(Request request, Response response) throws Exception {

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

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

    public ModelAndView nuevoPresupuesto(Request request, Response response) throws Exception {
    	// FALTA DAO PARA MOSTRAR OPERACIONES DE EGRESO

        String usuarioID = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioID);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();
    	    
    	//List<OperacionEgreso> operacionesEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(id);   
    		 List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("categorias", categorias);
            parametros.put("usuarioId", request.queryParams("usuarioId"));
            //parametros.put("egreso", operacionEgreso);
            parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

            return new ModelAndView( parametros, "nuevoPresupuesto.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {
        
       
        DocumentoComercial documentoComercial = this.crearDocumentoComercial(request);
        Organizacion organizacion = organizacionDao.getOrganizacionPorUsuarioId(new Integer(request.queryParams("usuarioId")));
        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        Boolean esElElegido = this.getEsElElegido(request);
        

        
        PresupuestoBuilder builder = new PresupuestoBuilder();
        
        builder.setDetalle(detallesEgresos);
        //builder.setEgreso(egreso);
        builder.setEsElElegido(esElElegido);
      
        Presupuesto presupuesto = builder.build();

        this.presupuestoDAO.guardarPresupuesto(presupuesto);

        response.redirect("/operacionesEgreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
        
        
        
        
        
        
    }

    private Boolean getEsElElegido(Request request) {		
    	             
          Boolean respuesta = new Boolean(request.queryParams("prespuestoElegidoCheck" ));            
       
		return respuesta;
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
