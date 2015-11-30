package pe.conadis.tradoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.AccionDAO;
import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.entity.Accion;
import pe.conadis.tradoc.service.AccionManager;

@Service
public class AccionManagerImpl extends ServiceImpl<Accion> implements AccionManager {

	@Autowired
	private AccionDAO accionDAO;
	
	private static final Logger logger = Logger.getLogger(AccionManagerImpl.class);
	
	@Override
	protected Dao<Accion> getDAO() {
		return accionDAO;
	}

	

}
