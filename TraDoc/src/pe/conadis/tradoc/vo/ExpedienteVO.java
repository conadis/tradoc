package pe.conadis.tradoc.vo;

import java.util.Date;



public class ExpedienteVO {
	
	//EXPEDIENTE
	private String codCorrelativo;
	private String codExpediente; //PK
	private String canDias;
	private String codUsuario;
	private String desAsunto;	
	private String desDocumento;
	private String desUtd;
	private String descripcion;
	private String detalle;
	private String fecCreacion;
	private String interesado;
	private String numFolios;
	private String indExpediente;//PREGUNTAR
	private String codEntidad;
	private String desEntidad;
	
	private String codEstadoExpediente;

	//NUMERACION DOCUMENTO
		
	private String anioDocumento;	
	private String indEstado;
	private String numDocumento;
	
	
	
	public String getDesEntidad() {
		return desEntidad;
	}
	public void setDesEntidad(String desEntidad) {
		this.desEntidad = desEntidad;
	}
	public String getCodCorrelativo() {
		return codCorrelativo;
	}
	public void setCodCorrelativo(String codCorrelativo) {
		this.codCorrelativo = codCorrelativo;
	}
	public String getCodExpediente() {
		return codExpediente;
	}
	public void setCodExpediente(String codExpediente) {
		this.codExpediente = codExpediente;
	}
	public String getCanDias() {
		return canDias;
	}
	public void setCanDias(String canDias) {
		this.canDias = canDias;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getDesAsunto() {
		return desAsunto;
	}
	public void setDesAsunto(String desAsunto) {
		this.desAsunto = desAsunto;
	}
	public String getDesDocumento() {
		return desDocumento;
	}
	public void setDesDocumento(String desDocumento) {
		this.desDocumento = desDocumento;
	}
	public String getDesUtd() {
		return desUtd;
	}
	public void setDesUtd(String desUtd) {
		this.desUtd = desUtd;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(String fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
	public String getInteresado() {
		return interesado;
	}
	public void setInteresado(String interesado) {
		this.interesado = interesado;
	}
	public String getNumFolios() {
		return numFolios;
	}
	public void setNumFolios(String numFolios) {
		this.numFolios = numFolios;
	}
	public String getIndExpediente() {
		return indExpediente;
	}
	public void setIndExpediente(String indExpediente) {
		this.indExpediente = indExpediente;
	}
	public String getCodEntidad() {
		return codEntidad;
	}
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	public String getCodEstadoExpediente() {
		return codEstadoExpediente;
	}
	public void setCodEstadoExpediente(String codEstadoExpediente) {
		this.codEstadoExpediente = codEstadoExpediente;
	}
	public String getAnioDocumento() {
		return anioDocumento;
	}
	public void setAnioDocumento(String anioDocumento) {
		this.anioDocumento = anioDocumento;
	}
	public String getIndEstado() {
		return indEstado;
	}
	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
}
