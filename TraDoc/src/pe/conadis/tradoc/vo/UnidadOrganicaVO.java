package pe.conadis.tradoc.vo;

import java.util.List;

public class UnidadOrganicaVO {
	
	private String codUO;
	private String desUO;
	private List<AccionVO> listaAccion;
	
	public String getCodUO() {
		return codUO;
	}
	public void setCodUO(String codUO) {
		this.codUO = codUO;
	}
	public String getDesUO() {
		return desUO;
	}
	public void setDesUO(String desUO) {
		this.desUO = desUO;
	}
	public List<AccionVO> getListaAccion() {
		return listaAccion;
	}
	public void setListaAccion(List<AccionVO> listaAccion) {
		this.listaAccion = listaAccion;
	}
	
	

}
