package pe.conadis.tradoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.conadis.tradoc.dao.AccionDAO;
import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.entity.Accion;
import pe.conadis.tradoc.service.AccionManager;

public class AccionManagerImpl extends ServiceImpl<Accion> implements AccionManager {

	@Autowired
	private AccionDAO accionDAO;
	
	@Override
	protected Dao<Accion> getDAO() {
		return accionDAO;
	}

	

}
