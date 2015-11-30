package pe.conadis.tradoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.dao.DerivacionAccionDAO;
import pe.conadis.tradoc.entity.DerivacionAccion;
import pe.conadis.tradoc.service.DerivacionAccionManager;

@Service
public class DerivacionAccionManagerImpl extends ServiceImpl<DerivacionAccion> implements DerivacionAccionManager {

	@Autowired
	private DerivacionAccionDAO derivacionAccionDAO;
	
	private static final Logger logger = Logger.getLogger(DerivacionAccionManagerImpl.class);
	
	
	@Override
	protected Dao<DerivacionAccion> getDAO() {
		// TODO Auto-generated method stub
		return derivacionAccionDAO;
	}

	
}
