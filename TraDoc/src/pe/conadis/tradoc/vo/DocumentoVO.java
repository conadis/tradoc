package pe.conadis.tradoc.vo;

import java.util.ArrayList;
import java.util.List;




public class DocumentoVO {

	//EN PANTALLA REGISTRO
	private String codTipoTramite;//CAMPO:TIPO DE TRAMITE
	private String fechaCreacion;//CAMPO:FECHA DE REGISTRO
	private String codUsuCreacion;//CAMPO:CREADO POR
	private String codEntidad;//CAMPO HIDDEN : ENVIADO POR, SE USARA PARA EL AGREGAR
	private String desEntidadExtUO;//CAMPO: ENVIADO POR (PUEDE SER ENTIDAD EXTERNO O UO)
	private String desNumDocumento;//CAMPO:NRO. DOCUMENTO
	List<UnidadOrganicaVO> listaUO = new ArrayList<UnidadOrganicaVO>();//LISTA DE UO , CAMPO: DIRIGIDO A
	private String desUO;//CAMPO: DIRIGIDO A
	private String descripcion;//CAMPO: DESCRIPCION

	
	//DATOS COMPLEMENTARIOS DEL DOCUMENTO 
	private String codDocumento;//PK
	private String codExpediente;//PF EXPEDIENTE
	private String codEstDocumento;
	private String desEstDocumento;
	private String codTipDocumento;	
	private String codUsuModificacion;
	private String indDocExterno;
	//flag documento en sesion
	private String docSession;
	
	
	public String getCodTipoTramite() {
		return codTipoTramite;
	}
	public void setCodTipoTramite(String codTipoTramite) {
		this.codTipoTramite = codTipoTramite;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCodUsuCreacion() {
		return codUsuCreacion;
	}
	public void setCodUsuCreacion(String codUsuCreacion) {
		this.codUsuCreacion = codUsuCreacion;
	}
	public String getCodEntidad() {
		return codEntidad;
	}
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	public String getDesEntidadExtUO() {
		return desEntidadExtUO;
	}
	public void setDesEntidadExtUO(String desEntidadExtUO) {
		this.desEntidadExtUO = desEntidadExtUO;
	}
	public String getDesNumDocumento() {
		return desNumDocumento;
	}
	public void setDesNumDocumento(String desNumDocumento) {
		this.desNumDocumento = desNumDocumento;
	}
	public List<UnidadOrganicaVO> getListaUO() {
		return listaUO;
	}
	public void setListaUO(List<UnidadOrganicaVO> listaUO) {
		this.listaUO = listaUO;
	}
	public String getDesUO() {
		return desUO;
	}
	public void setDesUO(String desUO) {
		this.desUO = desUO;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodDocumento() {
		return codDocumento;
	}
	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}
	public String getCodExpediente() {
		return codExpediente;
	}
	public void setCodExpediente(String codExpediente) {
		this.codExpediente = codExpediente;
	}
	public String getCodEstDocumento() {
		return codEstDocumento;
	}
	public void setCodEstDocumento(String codEstDocumento) {
		this.codEstDocumento = codEstDocumento;
	}
	public String getDesEstDocumento() {
		return desEstDocumento;
	}
	public void setDesEstDocumento(String desEstDocumento) {
		this.desEstDocumento = desEstDocumento;
	}
	public String getCodTipDocumento() {
		return codTipDocumento;
	}
	public void setCodTipDocumento(String codTipDocumento) {
		this.codTipDocumento = codTipDocumento;
	}
	public String getCodUsuModificacion() {
		return codUsuModificacion;
	}
	public void setCodUsuModificacion(String codUsuModificacion) {
		this.codUsuModificacion = codUsuModificacion;
	}
	public String getIndDocExterno() {
		return indDocExterno;
	}
	public void setIndDocExterno(String indDocExterno) {
		this.indDocExterno = indDocExterno;
	}
	public String getDocSession() {
		return docSession;
	}
	public void setDocSession(String docSession) {
		this.docSession = docSession;
	}
	
	
	
	
	
	
	
}
