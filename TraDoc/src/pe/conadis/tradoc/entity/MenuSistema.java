package pe.conadis.tradoc.entity;
// default package
// Generated 12/11/2015 12:56:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MenuSistema generated by hbm2java
 */
@Entity
@Table(name = "MENU_SISTEMA")
public class MenuSistema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codMenu;
	private Sistema sistema;
	private String desMenu;
	private String indEstado;
	private Set<OpcionMenu> opcionMenus = new HashSet<OpcionMenu>(0);

	public MenuSistema() {
	}

	public MenuSistema(Integer codMenu) {
		this.codMenu = codMenu;
	}

	public MenuSistema(Integer codMenu, Sistema sistema, String desMenu,
			String indEstado, Set<OpcionMenu> opcionMenus) {
		this.codMenu = codMenu;
		this.sistema = sistema;
		this.desMenu = desMenu;
		this.indEstado = indEstado;
		this.opcionMenus = opcionMenus;
	}

	@Id
	@Column(name = "COD_MENU", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getCodMenu() {
		return this.codMenu;
	}

	public void setCodMenu(Integer codMenu) {
		this.codMenu = codMenu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SISTEMA")
	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	@Column(name = "DES_MENU", length = 100)
	public String getDesMenu() {
		return this.desMenu;
	}

	public void setDesMenu(String desMenu) {
		this.desMenu = desMenu;
	}

	@Column(name = "IND_ESTADO", length = 1)
	public String getIndEstado() {
		return this.indEstado;
	}

	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuSistema")
	public Set<OpcionMenu> getOpcionMenus() {
		return this.opcionMenus;
	}

	public void setOpcionMenus(Set<OpcionMenu> opcionMenus) {
		this.opcionMenus = opcionMenus;
	}

}
