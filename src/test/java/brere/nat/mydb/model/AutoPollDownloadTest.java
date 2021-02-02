package brere.nat.mydb.model;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brere.nat.mydb.AbstractTest;

@TestMethodOrder(OrderAnnotation.class)
class AutoPollDownloadTest extends AbstractTest {

	private static AutoPollSeries autoPollSeries;
	private static AutoPollDownload item;

	private static final Logger LOG = LoggerFactory.getLogger(AutoPollDownloadTest.class);

	@BeforeAll
	public static void setUp() throws Exception {
		LOG.info("Setting up ");
		autoPollSeries = new AutoPollSeries();
		autoPollSeries.setActive(false);
		autoPollSeries.setFolderName("Test" + new Date().getTime());
		autoPollSeries.setImdbID("Test ID" + new Date().getTime());
		autoPollSeries.setPosterUrl("Nothing to see here");
		autoPollSeries.setTitle("Test Title");
		autoPollSeries.setYear("Test Year");
		autoPollSeries.setStartPoll(false);

		insert(autoPollSeries);
		
		LOG.info("Finished Setting up ");
	}

	@AfterAll
	public static void tearDown() throws Exception {
		remove(item);
		remove(autoPollSeries);
	}

	@Test
	@Order(1)
	void atestInsert() {
		LOG.info("Starting Test Insert");
		try {
			item = new AutoPollDownload(autoPollSeries);
			item.setEpisode(1);
			item.setSeason(1);
			autoPollSeries.getActiveDownloads().add(item);
			insert(item);
			LOG.info("Finished Inserting");
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			fail("Exception when Inserting AutoDownload");
		}
	}
	
	@Test
	@Order(2)
	void getAll() {
		LOG.info("Starting Test");
		try {
			final TypedQuery<AutoPollSeries> getAll = em.createNamedQuery("AutoPollSeries.getAll", AutoPollSeries.class);
			
			List<AutoPollSeries> all = getAll.getResultList();
			LOG.info("All Size :" + all.size());
			for (AutoPollSeries autoPoll : all) {
				for (AutoPollDownload downloads : autoPoll.getActiveDownloads()) {
					LOG.info("Series :" + autoPoll.getId() + " Download :" + downloads.getId());
				}
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			fail("Exception");
		}
	}

}
