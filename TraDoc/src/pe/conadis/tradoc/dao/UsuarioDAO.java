package pe.conadis.tradoc.dao;

import java.util.List;

import pe.conadis.tradoc.entity.OpcionMenu;
import pe.conadis.tradoc.entity.Usuario;



public interface UsuarioDAO extends Dao<Usuario>{

	public Usuario verifyPass(Usuario usuario);
	public List<OpcionMenu> obtenerOpcionMenu(Usuario usuario) throws Exception;
	public List<Usuario> buscarUsuario(Usuario usuario);
	public Usuario validaUsuario(Usuario usuario);
	

}