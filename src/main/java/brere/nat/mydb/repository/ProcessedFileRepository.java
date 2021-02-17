package brere.nat.mydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.ProcessedFile;

@Repository
public interface ProcessedFileRepository extends JpaRepository<ProcessedFile, Integer> {

}
