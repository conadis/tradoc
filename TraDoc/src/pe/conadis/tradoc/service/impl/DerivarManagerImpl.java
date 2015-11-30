package pe.conadis.tradoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.conadis.tradoc.dao.Dao;
import pe.conadis.tradoc.dao.DerivarDAO;
import pe.conadis.tradoc.entity.Derivar;
import pe.conadis.tradoc.service.DerivarManager;

@Service
public class DerivarManagerImpl extends ServiceImpl<Derivar> implements DerivarManager {

	@Autowired
	private DerivarDAO derivarDAO;
	
	private static final Logger logger = Logger.getLogger(DerivarManagerImpl.class);
	
	@Override
	protected Dao<Derivar> getDAO() {
		return derivarDAO;
	}

	

}
