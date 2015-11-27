package pe.conadis.tradoc.dao;

import java.util.List;

import pe.conadis.tradoc.entity.Expediente;
import pe.conadis.tradoc.entity.UnidadOrganica;
import pe.conadis.tradoc.entity.Usuario;
import pe.conadis.tradoc.vo.DocumentoVO;
import pe.conadis.tradoc.vo.ExpedienteVO;

public interface ExpedienteDAO extends Dao<Expediente>{
	
	public List<Expediente> searchListExpediente(ExpedienteVO expedienteVO) throws Exception;
	public List<DocumentoVO> listDocumentoExterno(Expediente expediente,String codUOusuario) throws Exception;
	public List<UnidadOrganica> listUOporUsuario(Usuario usuario) throws Exception;

}
