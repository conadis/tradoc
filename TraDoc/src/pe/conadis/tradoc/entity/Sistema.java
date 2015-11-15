package pe.conadis.tradoc.entity;
// default package
// Generated 12/11/2015 12:56:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sistema generated by hbm2java
 */
@Entity
@Table(name = "SISTEMA")
public class Sistema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codSistema;
	private String desSistema;
	private String indEstado;
	private Set<MenuSistema> menuSistemas = new HashSet<MenuSistema>(0);

	public Sistema() {
	}

	public Sistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Sistema(Integer codSistema, String desSistema,
			String indEstado, Set<MenuSistema> menuSistemas) {
		this.codSistema = codSistema;
		this.desSistema = desSistema;
		this.indEstado = indEstado;
		this.menuSistemas = menuSistemas;
	}

	@Id
	@Column(name = "COD_SISTEMA", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getCodSistema() {
		return this.codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	@Column(name = "DES_SISTEMA", length = 100)
	public String getDesSistema() {
		return this.desSistema;
	}

	public void setDesSistema(String desSistema) {
		this.desSistema = desSistema;
	}

	@Column(name = "IND_ESTADO", length = 1)
	public String getIndEstado() {
		return this.indEstado;
	}

	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sistema")
	public Set<MenuSistema> getMenuSistemas() {
		return this.menuSistemas;
	}

	public void setMenuSistemas(Set<MenuSistema> menuSistemas) {
		this.menuSistemas = menuSistemas;
	}

}
