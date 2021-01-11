package brere.nat.mydb.model;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brere.nat.mydb.AbstractTest;

class AutoPollSeriesTest extends AbstractTest {

	private static AutoPollSeries autoPollSeries;
	private static AutoPollDownload autoPollDownload;
	private static AutoPollSeries autoPollSeries2;
	
	private static final Logger LOG = LoggerFactory.getLogger(AutoPollSeriesTest.class);

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

		autoPollDownload = new AutoPollDownload(autoPollSeries);
		
		autoPollDownload.setAutoPollSeries(autoPollSeries);
		autoPollDownload.setEpisode(1);
		autoPollDownload.setSeason(1);
		
		autoPollSeries.getActiveDownloads().add(autoPollDownload);
		
		
		autoPollSeries2 = new AutoPollSeries();
		
		autoPollSeries2.setActive(false);
		autoPollSeries2.setFolderName("Test2" + new Date().getTime());
		autoPollSeries2.setImdbID("Test ID2" + new Date().getTime());
		autoPollSeries2.setPosterUrl("Nothing to see here");
		autoPollSeries2.setTitle("Test Title2");
		autoPollSeries2.setYear("Test Year2");
		
		insert(autoPollSeries, autoPollDownload, autoPollSeries2);
		LOG.info("Finished Setting up ");
		
		
	}

	@AfterAll
	public static void tearDown() throws Exception {
		remove(autoPollDownload);
		remove(autoPollSeries);
		remove(autoPollSeries2);
	}

	@Test
	void getAll() {
		LOG.info("Starting Test");
		try {
			List<AutoPollSeries> all = AutoPollSeries.Queries.getAll();
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
	
	@Test
	void getAllByActive() {
		LOG.info("Starting Test");
		try {
			List<AutoPollSeries> all = AutoPollSeries.Queries.getAllByActive(false);
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
