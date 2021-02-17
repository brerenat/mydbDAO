package brere.nat.mydb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.AutoPollSeries;

@Repository
public interface AutoPollSeriesRepository extends JpaRepository<AutoPollSeries, Integer> {

	List<AutoPollSeries> getAll();
	List<AutoPollSeries> getAllByActive(boolean active);
	List<AutoPollSeries> getAllByStartPoll(boolean startPoll);
	long countAll();
	AutoPollSeries getByID(Integer id);
	List<AutoPollSeries> getAllSearch(String title);
	long countSearch(String title);
	List<AutoPollSeries> getAllSorted(String sorting);
	List<AutoPollSeries> getAllSearchSorted(String title, String sorting);
	
}
