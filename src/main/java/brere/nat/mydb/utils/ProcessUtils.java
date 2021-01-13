package brere.nat.mydb.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brere.nat.mydb.model.FileType;
import brere.nat.mydb.model.ProcessedFile;

public class ProcessUtils {

	private static EntityManagerFactory emf;
	
	private static final Logger LOG = LoggerFactory.getLogger(ProcessUtils.class);
	
	public static EntityManager getEm() {
		return emf.createEntityManager();
	}

	public static void setEmf(EntityManagerFactory emf) {
		ProcessUtils.emf = emf;
	}
	
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	public static void updateDatebase(final String destination, final String fileTypeStr) {
		final EntityManager em = ProcessUtils.getEm();

		if (em != null) {
			final EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			FileType fileType;
			try {
				fileType = FileType.Queries.findWithName(fileTypeStr);
			} catch (NoResultException e) {
				LOG.warn("No Existing File Type :" + fileTypeStr);
				fileType = new FileType();
				fileType.setType(fileTypeStr);
				em.persist(fileType);
			}

			ProcessedFile procFile;
			try {
				procFile = ProcessedFile.Queries.findWithName(destination);
			} catch (NoResultException e) {
				LOG.warn("No Existing File with Name :" + destination);
				procFile = new ProcessedFile();
				em.persist(fileType);
				procFile.setFileName(destination);
			}
			procFile.setDateProcessed(new Date());
			procFile.setFileType(fileType);

			em.persist(procFile);
			transaction.commit();
		}
	}
}
