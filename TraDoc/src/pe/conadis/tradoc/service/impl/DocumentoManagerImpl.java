package pe.conadis.tradoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.dao.DocumentoDAO;
import pe.conadis.tradoc.entity.Documento;
import pe.conadis.tradoc.service.DocumentoManager;

@Service
public class DocumentoManagerImpl extends ServiceImpl<Documento> implements DocumentoManager {

	@Autowired
	private DocumentoDAO documentoDAO;
	
	private static final Logger logger = Logger.getLogger(DocumentoManagerImpl.class);
	
	@Override
	protected Dao<Documento> getDAO() {
		// TODO Auto-generated method stub
		return documentoDAO;
	}

	

}
