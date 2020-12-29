package com.home.mydb.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.mydb.model.FileType;
import com.home.mydb.model.ProcessedFile;

public class ProcessUtils {

	private static EntityManager em;
	
	private static final Logger LOG = LoggerFactory.getLogger(ProcessUtils.class);
	
	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		ProcessUtils.em = em;
	}

	public static void updateDatebase(final String destination, final String fileTypeStr) {
		final EntityManager em = ProcessUtils.getEm();

		if (em != null) {
			em.getTransaction().begin();

			FileType fileType;
			try {
				fileType = FileType.findWithName(em, fileTypeStr);
			} catch (NoResultException e) {
				LOG.warn("No Existing File Type :" + fileTypeStr);
				fileType = new FileType();
				fileType.setType(fileTypeStr);
				em.persist(fileType);
			}

			ProcessedFile procFile;
			try {
				procFile = ProcessedFile.findWithName(em, destination);
			} catch (NoResultException e) {
				LOG.warn("No Existing File with Name :" + destination);
				procFile = new ProcessedFile();
				em.persist(fileType);
				procFile.setFileName(destination);
			}
			procFile.setDateProcessed(new Date());
			procFile.setFileType(fileType);

			em.persist(procFile);
			em.getTransaction().commit();
		}
	}
}
