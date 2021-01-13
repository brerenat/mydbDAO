package brere.nat.mydb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;

import brere.nat.mydb.utils.ProcessUtils;

public abstract class AbstractTest {
	
	protected static EntityManager em;
	
	@BeforeAll
	public static void beforeClass() {
		if (ProcessUtils.getEmf() == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("torrentmover");
			ProcessUtils.setEmf(factory);
		}
		em = ProcessUtils.getEm();
	}
	
	protected static void insert(final Object... obj) {
		final EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for (final Object item : obj) {
			em.persist(item);
		}
		transaction.commit();
	}
	
	protected static void remove(final Object obj) {
		final EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(obj);
		transaction.commit();
	}

}
