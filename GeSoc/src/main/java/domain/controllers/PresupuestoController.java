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

public class PresupuestoController {

    private ProveedorDAO proveedorDAO = new ProveedorDAOMySQL();
    private OrganizacionDAO organizacionDao = new OrganizacionDAOMemoria();
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMySQL();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
    private PresupuestoDAO presupuestoDAO = new PresupuestoDAOMySQL();
    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOMySQL();
    private ImagenesDAO imagenesDAO = new ImagenesDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();
    private CriterioDeOperacionesDAO criterioDeOperacionesDAO = new CriterioDeOperacionesDAOMySQL();

    
    public ModelAndView mostrarPresupuestos(Request request, Response response)  throws Exception {

        String usuarioIDSpark = request.session().attribute("id");
        
        Integer idEgreso = new Integer(request.queryParams("egresoId"));

        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        List<CriterioDeOperaciones> criteriosDeOperaciones = this.criterioDeOperacionesDAO.getTodasLosCriterios();
  
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();

        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();
    
        List<Presupuesto> presupuestos= this.presupuestoDAO.buscarPresupuestoEgresoId(idEgreso);
        
        this.arreglarImagenPresupuestos(presupuestos);

        Map<String, Object> parametros = new HashMap<>();
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("categorias", categorias);
        parametros.put("presupuestos", presupuestos);
        parametros.put("criteriosDeOperaciones", criteriosDeOperaciones);
        parametros.put("tiposCombantes", tipoComprobanteList);

        return new ModelAndView(parametros, "presupuestos.hbs");
    }


    public ModelAndView editarPresupuesto(Request request, Response response) throws Exception {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Integer id = new Integer(request.queryParams("presupuestoId"));

        Presupuesto presupuesto = this.presupuestoDAO.buscarPresupuesto(id);
        
        
        
        OperacionEgreso operacionEgreso = this.operacionEgresoDAO.buscarOperacionEgresoPorId(presupuesto.getEgreso().getId());

        List<Proveedor> proveedores = this.proveedorDAO.getTodosLosProveedores();
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();

        Map<String, Object> parametros = new HashMap<>();
        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("presupuesto", presupuesto);
        parametros.put("provedoores", proveedores);
        parametros.put("categorias", categorias);
        parametros.put("egreso", operacionEgreso);

        return new ModelAndView( parametros, "nuevoPresupuesto.hbs");
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

        response.redirect("/operacionesEgreso");
        return response;
    }

    public ModelAndView nuevoPresupuesto(Request request, Response response) throws Exception {
    	

        //String usuarioID = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Integer organizacionId =  usuario.getRol().getOrganizacion().getId();        

    	List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();

        Map<String, Object> parametros = new HashMap<>();
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("egresoId", request.queryParams("egresoId"));


       return new ModelAndView( parametros, "nuevoPresupuesto.hbs");

    }

    public Response guardar(Request request, Response response) throws Exception {
       
    	String usuarioIDSpark = request.session().attribute("id");       
       
               
        List<DetalleEgreso> detallesEgresos = this.getListaDeDetalle(request);
        
        Boolean esElElegido = this.getEsElElegido(request);
        
        OperacionEgreso egreso= this.operacionEgresoDAO.buscarOperacionEgresoPorId(new Integer(request.queryParams("egreso")));
        
        Integer egresoId = new Integer(request.queryParams("egreso"));
       
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        
        List<CategoriaDeOperaciones> categoriasDeOperaciones = this.getListaDeCategorias(request);
        
        
        PresupuestoBuilder builder = new PresupuestoBuilder();
        builder.setCategoriasAsociadas(categoriasDeOperaciones);
        builder.setFecha(fecha);
        builder.setDetalle(detallesEgresos);
        builder.setEgreso(egreso);
        builder.setEsElElegido(esElElegido);
      
        Presupuesto presupuesto = builder.build();

        this.presupuestoDAO.guardarPresupuesto(presupuesto);

        response.redirect("/presupuestos?egresoId="+egresoId);
        return response;
        
             
        
        
        
    }

    private Boolean getEsElElegido(Request request) {		
    	             
          Boolean respuesta = new Boolean(request.queryParams("prespuestoElegidoCheck" ));            
       
		return respuesta;
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

    private DocumentoComercial crearDocumentoComercial(Request request) {
        DocumentoComercial documentoComercial = new DocumentoComercial();

        switch (request.queryParams("documentoComercialTipo")) {
            case "Fisico":
                TipoComprobante tipoComprobante = new TipoComprobante();
                tipoComprobante.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumento = new Long(request.queryParams("documentoComercialNumero"));
                documentoComercial.guardarDocumentoFisico(tipoComprobante, numeroDocumento);
                documentoComercial.setContent(request.queryParams("documentoComercialAdjunto").getBytes());
                break;
            case "Digital":
            	TipoComprobante tipoComprobanteDigital = new TipoComprobante();
            	tipoComprobanteDigital.setDescripcion(request.queryParams("documentoComercialClase"));
                Long numeroDocumentoDigital = new Long(request.queryParams("documentoComercialNumero"));
                documentoComercial.altaDocumentoComercial(tipoComprobanteDigital, numeroDocumentoDigital,"Digital",request.queryParams("documentoComercialAdjunto").getBytes());
                        
                break;
        }

        return documentoComercial;
    }
    
    private void arreglarImagenPresupuestos(List<Presupuesto> presupuestos) {
    	presupuestos.forEach( presupuesto -> {
                try {
                    String content = this.imagenesDAO.buscarContenido(presupuesto.getId());
                    System.out.println("DOCUMENTO: " + content);

                    presupuesto.getDocumentoComercial().setContentDeserealizado(content);
                } catch (Exception e){}

            });

    }
    
}
