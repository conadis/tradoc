package pe.conadis.tradoc.dao;

import java.util.List;

import pe.conadis.tradoc.entity.MenuSistema;

public interface MenuSistemaDAO {
	public List<MenuSistema> obtenerMenuSistema(String strCodigoUsuario) throws Exception;
}
