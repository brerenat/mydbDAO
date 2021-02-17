package brere.nat.mydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.AutoPollDownload;

@Repository
public interface AutoPollDownloadRepository extends JpaRepository<AutoPollDownload, Integer> {

}
