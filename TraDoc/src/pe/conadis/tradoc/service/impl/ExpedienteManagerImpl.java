package pe.conadis.tradoc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.dao.ExpedienteDAO;
import pe.conadis.tradoc.entity.Expediente;
import pe.conadis.tradoc.entity.UnidadOrganica;
import pe.conadis.tradoc.entity.Usuario;
import pe.conadis.tradoc.service.ExpedienteManager;
import pe.conadis.tradoc.vo.DocumentoVO;
import pe.conadis.tradoc.vo.ExpedienteVO;

@Service
public class ExpedienteManagerImpl extends ServiceImpl<Expediente> implements ExpedienteManager{

	@Autowired
	private ExpedienteDAO expedienteDAO;
	
	private static final Logger logger = Logger.getLogger(ExpedienteManagerImpl.class);
	
	@Override
	protected Dao<Expediente> getDAO() {
		return expedienteDAO;
	}

	@Override
	public List<Expediente> searchListExpediente(ExpedienteVO expedienteVO)
			throws Exception {
		return expedienteDAO.searchListExpediente(expedienteVO);
	}
	public List<UnidadOrganica> listUOporUsuario(Usuario usuario) throws Exception{
		return expedienteDAO.listUOporUsuario(usuario);
	}
	
	public List<DocumentoVO> listDocumentoExterno(Expediente expediente,String codUOusuario) throws Exception{
		return expedienteDAO.listDocumentoExterno(expediente, codUOusuario);
				
	}

}
