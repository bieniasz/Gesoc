package domain.entities.ProveedorDocComer;


import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class DocumentoComercial extends EntidadPersistente {

    public DocumentoComercial() {
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
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

	public void setContent(String contenido) {
		 this.content=contenido;
		
	}

    public TipoComprobante getTipoDocumentoComercial() {
        return tipoDocumentoComercial;
    }

    public void setTipoDocumentoComercial(TipoComprobante tipoDocumentoComercial) {
        this.tipoDocumentoComercial = tipoDocumentoComercial;
    }

    public Long getNumeroDocumentoComercial() {
        return numeroDocumentoComercial;
    }

    public void setNumeroDocumentoComercial(Long numeroDocumentoComercial) {
        this.numeroDocumentoComercial = numeroDocumentoComercial;
    }

    public String getTipoDeAdjunto() {
        return tipoDeAdjunto;
    }

    public void setTipoDeAdjunto(String tipoDeAdjunto) {
        this.tipoDeAdjunto = tipoDeAdjunto;
    }

    public String getContent() {
        return content;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
