package pe.conadis.tradoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.dao.NumeracionDocumentoDAO;
import pe.conadis.tradoc.entity.NumeracionDocumento;
import pe.conadis.tradoc.service.NumeracionDocumentoManager;

@Service
public class NumeracionDocumentoManagerImpl extends ServiceImpl<NumeracionDocumento> implements
		NumeracionDocumentoManager {

	@Autowired
	private NumeracionDocumentoDAO numeracionDocumentoDAO;
	
	private static final Logger logger = Logger.getLogger(NumeracionDocumentoManagerImpl.class);
	
	@Override
	protected Dao<NumeracionDocumento> getDAO() {
		return numeracionDocumentoDAO;
	}

	

}
