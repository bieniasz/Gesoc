package ProveedorDocComer;


import operacionComercial.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class DocumentoComercial extends EntidadPersistente {

    @ManyToOne
    private TipoComprobante tipoDocumentoComercial;

    @Column
    private Long numeroDocumentoComercial;

    @Column
    private String tipoDeAdjunto;

    @Column
    private String content;

    @Column
    private boolean activo;


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
