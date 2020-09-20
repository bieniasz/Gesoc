package ProveedorDocComer;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class DocumentoComercial {

    @Transient
    private TipoComprobante tipoDocumentoComercial;
    private Long numeroDocumentoComercial;
    private String tipoDeAdjunto;
    private String content;


    public void guardarDocumentoFisico(TipoComprobante tipo,Long numeroDocumento,String contenido){

        DocumentoComercial DocFisico = new DocumentoComercial();
        DocFisico.tipoDocumentoComercial = tipo;
        DocFisico.numeroDocumentoComercial=numeroDocumento;
        DocFisico.tipoDeAdjunto="Fisico";
        DocFisico.content=contenido;

    }

    public void altaDocumentoComercial(TipoComprobante tipo,Long numeroDoc,String tipoDeAdjunto,String contenido){
        DocumentoComercial Documento = new DocumentoComercial();
        Documento.tipoDocumentoComercial = tipo;
        Documento.numeroDocumentoComercial = numeroDoc;
        Documento.tipoDeAdjunto = tipoDeAdjunto;
        Documento.content=contenido;
    }
}
