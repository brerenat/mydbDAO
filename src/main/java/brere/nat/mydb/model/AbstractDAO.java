package brere.nat.mydb.model;

import javax.persistence.EntityManager;

import brere.nat.mydb.utils.ProcessUtils;

public abstract class AbstractDAO {

	protected static EntityManager getEm() {
		return ProcessUtils.getEm();
	}
	
}
